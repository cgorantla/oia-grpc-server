/*******************************************************************************
 * This file is part of OpenNMS(R).
 *
 * Copyright (C) 2020 The OpenNMS Group, Inc.
 * OpenNMS(R) is Copyright (C) 1999-2020 The OpenNMS Group, Inc.
 *
 * OpenNMS(R) is a registered trademark of The OpenNMS Group, Inc.
 *
 * OpenNMS(R) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 *
 * OpenNMS(R) is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with OpenNMS(R).  If not, see:
 *      http://www.gnu.org/licenses/
 *
 * For more information contact:
 *     OpenNMS(R) Licensing <license@opennms.org>
 *     http://www.opennms.org/
 *     http://www.opennms.com/
 *******************************************************************************/

package org.opennms.integration.api.v1.grpc;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.opennms.integration.api.v1.config.events.AlarmType;
import org.opennms.integration.api.v1.model.Alarm;
import org.opennms.integration.api.v1.model.DatabaseEvent;
import org.opennms.integration.api.v1.model.EventParameter;
import org.opennms.integration.api.v1.model.InMemoryEvent;
import org.opennms.integration.api.v1.model.IpInterface;
import org.opennms.integration.api.v1.model.MetaData;
import org.opennms.integration.api.v1.model.Node;
import org.opennms.integration.api.v1.model.Severity;
import org.opennms.integration.api.v1.model.SnmpInterface;
import org.opennms.integration.api.v1.model.immutables.ImmutableEventParameter;
import org.opennms.integration.api.v1.model.immutables.ImmutableInMemoryEvent;
import org.opennms.integration.api.v1.proto.Model;

public class ModelConverter {

    public static Model.Alarm buildAlarmProto(Alarm alarm) {
        Model.Alarm.Builder alarmBuilder = Model.Alarm.newBuilder();
        Optional.ofNullable(alarm.getId()).ifPresent(alarmBuilder::setId);
        Optional.ofNullable(alarm.getNode()).ifPresent(node ->
                alarmBuilder.setNode(convertNode(node)));
        Optional.ofNullable(alarm.getReductionKey()).ifPresent(alarmBuilder::setReductionKey);
        Optional.ofNullable(alarm.getDescription()).ifPresent(alarmBuilder::setDescription);
        Optional.ofNullable(alarm.getLogMessage()).ifPresent(alarmBuilder::setLogMessage);
        Optional.ofNullable(alarm.getManagedObjectInstance()).ifPresent(alarmBuilder::setManagedObjectInstance);
        Optional.ofNullable(alarm.getManagedObjectType()).ifPresent(alarmBuilder::setManagedObjectInstance);
        Optional.ofNullable(convertAlarmType(alarm.getType())).ifPresent(alarmBuilder::setType);
        Optional.ofNullable(convertSeverity(alarm.getSeverity())).ifPresent(alarmBuilder::setSeverity);
        Optional.ofNullable(alarm.getFirstEventTime()).ifPresent(date -> alarmBuilder.setFirstEventTime(date.getTime()));
        Optional.ofNullable(alarm.getLastEventTime()).ifPresent(date -> alarmBuilder.setLastEventTime(date.getTime()));
        Optional.ofNullable(alarm.getLastEvent()).ifPresent(event ->
                alarmBuilder.setLastEvent(convertDatabaseEvent(event))
        );
        alarm.getAttributes().forEach(alarmBuilder::putAttributes);
        alarm.getRelatedAlarms().forEach(ModelConverter::buildAlarmProto);
        return alarmBuilder.build();
    }

    private static Model.AlarmType convertAlarmType(AlarmType alarmType) {
        if (alarmType == null) {
            return null;
        }
        switch (alarmType) {
            case PROBLEM:
                return Model.AlarmType.PROBLEM_WITH_CLEAR;
            case RESOLUTION:
                return Model.AlarmType.CLEAR;
            case PROBLEM_WITHOUT_RESOLUTION:
                return Model.AlarmType.PROBLEM_WITHOUT_CLEAR;
        }
        return null;
    }

    private static Model.Severity convertSeverity(Severity severity) {
        if (severity == null) {
            return null;
        }
        switch (severity) {
            case INDETERMINATE:
                return Model.Severity.INDETERMINATE;
            case CLEARED:
                return Model.Severity.CLEARED;
            case NORMAL:
                return Model.Severity.NORMAL;
            case WARNING:
                return Model.Severity.WARNING;
            case MINOR:
                return Model.Severity.MINOR;
            case MAJOR:
                return Model.Severity.MAJOR;
            case CRITICAL:
                return Model.Severity.CRITICAL;
        }
        return null;
    }

    private static Model.MetaData convertMetadata(MetaData metaData) {
        Model.MetaData.Builder builder = Model.MetaData.newBuilder();
        Optional.ofNullable(metaData.getContext()).ifPresent(builder::setContext);
        Optional.ofNullable(metaData.getKey()).ifPresent(builder::setKey);
        Optional.ofNullable(metaData.getValue()).ifPresent(builder::setValue);
        return builder.build();
    }

    private static Model.Node convertNode(Node node) {
        Model.Node.Builder builder = Model.Node.newBuilder();
        Optional.ofNullable(node.getId()).ifPresent(builder::setId);
        Optional.ofNullable(node.getForeignId()).ifPresent(builder::setForeignId);
        Optional.ofNullable(node.getForeignSource()).ifPresent(builder::setForeignSource);
        Optional.ofNullable(node.getLabel()).ifPresent(builder::setLabel);
        Optional.ofNullable(node.getLocation()).ifPresent(builder::setLocation);
        List<Model.MetaData> metaData = node.getMetaData().stream().map(ModelConverter::convertMetadata).collect(Collectors.toList());
        builder.addAllMetaData(metaData);
        List<Model.IPInterface> ipInterfaces = node.getIpInterfaces().stream().map(ModelConverter::convertIpInterface).collect(Collectors.toList());
        builder.addAllIpInterfaces(ipInterfaces);
        List<Model.SNMPInterface> snmpInterfaces = node.getSnmpInterfaces().stream().map(ModelConverter::convertSnmpInterface).collect(Collectors.toList());
        builder.addAllSnmpInterfaces(snmpInterfaces);
        return builder.build();
    }

    private static Model.SNMPInterface convertSnmpInterface(SnmpInterface snmpInterface) {
        Model.SNMPInterface.Builder builder = Model.SNMPInterface.newBuilder();
        Optional.ofNullable(snmpInterface.getIfDescr()).ifPresent(builder::setIfDescr);
        Optional.ofNullable(snmpInterface.getIfIndex()).ifPresent(builder::setIfIndex);
        Optional.ofNullable(snmpInterface.getIfName()).ifPresent(builder::setIfName);
        return builder.build();
    }

    private static Model.IPInterface convertIpInterface(IpInterface ipInterface) {
        Model.IPInterface.Builder builder = Model.IPInterface.newBuilder();
        Optional.ofNullable(ipInterface.getIpAddress()).ifPresent(iface -> builder.setIpAddress(iface.getHostAddress()));
        ipInterface.getSnmpInterface().ifPresent(snmpInterface -> {
            builder.setSnmpInterface(convertSnmpInterface(snmpInterface));
        });
        List<Model.MetaData> metaData = ipInterface.getMetaData().stream().map(ModelConverter::convertMetadata).collect(Collectors.toList());
        builder.addAllMetaData(metaData);
        return builder.build();
    }

    private static Model.EventParameter convertEventParameter(EventParameter eventParameter) {
        Model.EventParameter.Builder builder = Model.EventParameter.newBuilder();
        builder.setName(eventParameter.getName());
        builder.setValue(eventParameter.getValue());
        return builder.build();
    }

    private static EventParameter convertEventParameter(Model.EventParameter eventParameter) {
        return ImmutableEventParameter.newBuilder()
                .setName(eventParameter.getName())
                .setValue(eventParameter.getValue())
                .build();
    }

    private static Model.DatabaseEvent convertDatabaseEvent(DatabaseEvent databaseEvent) {
        Model.DatabaseEvent.Builder builder = Model.DatabaseEvent.newBuilder();
        Optional.ofNullable(databaseEvent.getId()).ifPresent(builder::setId);
        Optional.ofNullable(databaseEvent.getUei()).ifPresent(builder::setUei);
        List<Model.EventParameter> parameters = databaseEvent.getParameters().stream().map(ModelConverter::convertEventParameter).collect(Collectors.toList());
        builder.addAllParameters(parameters);
        return builder.build();
    }

    public static Model.InMemoryEvent convertInMemoryEvent(InMemoryEvent inMemoryEvent) {
        Model.InMemoryEvent.Builder builder = Model.InMemoryEvent.newBuilder();
        Optional.ofNullable(inMemoryEvent.getUei()).ifPresent(builder::setUei);
        Optional.ofNullable(inMemoryEvent.getSource()).ifPresent(builder::setSource);
        Optional.ofNullable(inMemoryEvent.getNodeId()).ifPresent(builder::setNodeId);
        Optional.ofNullable(convertSeverity(inMemoryEvent.getSeverity())).ifPresent(builder::setSeverity);
        List<Model.EventParameter> parameters = inMemoryEvent.getParameters().stream().map(ModelConverter::convertEventParameter).collect(Collectors.toList());
        builder.addAllParameters(parameters);
        return builder.build();
    }

    public static InMemoryEvent convertInMemoryEvent(Model.InMemoryEvent inMemoryEvent) {
        if (inMemoryEvent == null) {
            return null;
        }
        ImmutableInMemoryEvent.Builder eventBuilder = ImmutableInMemoryEvent.newBuilder()
                .setUei(inMemoryEvent.getUei())
                .setSource(inMemoryEvent.getSource())
                .setNodeId(Long.valueOf(inMemoryEvent.getNodeId()).intValue())
                .setSeverity(convertSeverity(inMemoryEvent.getSeverity()));
        inMemoryEvent.getParametersList().forEach(eventParameter -> {
            eventBuilder.addParameter(convertEventParameter(eventParameter));
        });
        return eventBuilder.build();
    }

    private static Severity convertSeverity(Model.Severity severity) {
        if (severity == null) {
            return null;
        }
        switch (severity) {
            case INDETERMINATE:
                return Severity.INDETERMINATE;
            case CLEARED:
                return Severity.CLEARED;
            case NORMAL:
                return Severity.NORMAL;
            case WARNING:
                return Severity.WARNING;
            case MINOR:
                return Severity.MINOR;
            case MAJOR:
                return Severity.MAJOR;
            case CRITICAL:
                return Severity.CRITICAL;
        }
        return null;
    }
}
