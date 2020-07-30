package org.opennms.integration.api.v1.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.24.0)",
    comments = "Source: events.proto")
public final class EventForwarderGrpc {

  private EventForwarderGrpc() {}

  public static final String SERVICE_NAME = "oia.EventForwarder";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.opennms.integration.api.v1.proto.Model.InMemoryEvent,
      org.opennms.integration.api.v1.proto.Model.Empty> getSendAsyncMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SendAsync",
      requestType = org.opennms.integration.api.v1.proto.Model.InMemoryEvent.class,
      responseType = org.opennms.integration.api.v1.proto.Model.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.opennms.integration.api.v1.proto.Model.InMemoryEvent,
      org.opennms.integration.api.v1.proto.Model.Empty> getSendAsyncMethod() {
    io.grpc.MethodDescriptor<org.opennms.integration.api.v1.proto.Model.InMemoryEvent, org.opennms.integration.api.v1.proto.Model.Empty> getSendAsyncMethod;
    if ((getSendAsyncMethod = EventForwarderGrpc.getSendAsyncMethod) == null) {
      synchronized (EventForwarderGrpc.class) {
        if ((getSendAsyncMethod = EventForwarderGrpc.getSendAsyncMethod) == null) {
          EventForwarderGrpc.getSendAsyncMethod = getSendAsyncMethod =
              io.grpc.MethodDescriptor.<org.opennms.integration.api.v1.proto.Model.InMemoryEvent, org.opennms.integration.api.v1.proto.Model.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SendAsync"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.opennms.integration.api.v1.proto.Model.InMemoryEvent.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.opennms.integration.api.v1.proto.Model.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new EventForwarderMethodDescriptorSupplier("SendAsync"))
              .build();
        }
      }
    }
    return getSendAsyncMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.opennms.integration.api.v1.proto.Model.InMemoryEvent,
      org.opennms.integration.api.v1.proto.Events.Ack> getSendSyncMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SendSync",
      requestType = org.opennms.integration.api.v1.proto.Model.InMemoryEvent.class,
      responseType = org.opennms.integration.api.v1.proto.Events.Ack.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.opennms.integration.api.v1.proto.Model.InMemoryEvent,
      org.opennms.integration.api.v1.proto.Events.Ack> getSendSyncMethod() {
    io.grpc.MethodDescriptor<org.opennms.integration.api.v1.proto.Model.InMemoryEvent, org.opennms.integration.api.v1.proto.Events.Ack> getSendSyncMethod;
    if ((getSendSyncMethod = EventForwarderGrpc.getSendSyncMethod) == null) {
      synchronized (EventForwarderGrpc.class) {
        if ((getSendSyncMethod = EventForwarderGrpc.getSendSyncMethod) == null) {
          EventForwarderGrpc.getSendSyncMethod = getSendSyncMethod =
              io.grpc.MethodDescriptor.<org.opennms.integration.api.v1.proto.Model.InMemoryEvent, org.opennms.integration.api.v1.proto.Events.Ack>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SendSync"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.opennms.integration.api.v1.proto.Model.InMemoryEvent.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.opennms.integration.api.v1.proto.Events.Ack.getDefaultInstance()))
              .setSchemaDescriptor(new EventForwarderMethodDescriptorSupplier("SendSync"))
              .build();
        }
      }
    }
    return getSendSyncMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static EventForwarderStub newStub(io.grpc.Channel channel) {
    return new EventForwarderStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static EventForwarderBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new EventForwarderBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static EventForwarderFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new EventForwarderFutureStub(channel);
  }

  /**
   */
  public static abstract class EventForwarderImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Send the given event asynchronously.
     * </pre>
     */
    public void sendAsync(org.opennms.integration.api.v1.proto.Model.InMemoryEvent request,
        io.grpc.stub.StreamObserver<org.opennms.integration.api.v1.proto.Model.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(getSendAsyncMethod(), responseObserver);
    }

    /**
     * <pre>
     * Send the given event synchronously with ack when it is successful.
     * </pre>
     */
    public void sendSync(org.opennms.integration.api.v1.proto.Model.InMemoryEvent request,
        io.grpc.stub.StreamObserver<org.opennms.integration.api.v1.proto.Events.Ack> responseObserver) {
      asyncUnimplementedUnaryCall(getSendSyncMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSendAsyncMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                org.opennms.integration.api.v1.proto.Model.InMemoryEvent,
                org.opennms.integration.api.v1.proto.Model.Empty>(
                  this, METHODID_SEND_ASYNC)))
          .addMethod(
            getSendSyncMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                org.opennms.integration.api.v1.proto.Model.InMemoryEvent,
                org.opennms.integration.api.v1.proto.Events.Ack>(
                  this, METHODID_SEND_SYNC)))
          .build();
    }
  }

  /**
   */
  public static final class EventForwarderStub extends io.grpc.stub.AbstractStub<EventForwarderStub> {
    private EventForwarderStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EventForwarderStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EventForwarderStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EventForwarderStub(channel, callOptions);
    }

    /**
     * <pre>
     * Send the given event asynchronously.
     * </pre>
     */
    public void sendAsync(org.opennms.integration.api.v1.proto.Model.InMemoryEvent request,
        io.grpc.stub.StreamObserver<org.opennms.integration.api.v1.proto.Model.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSendAsyncMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Send the given event synchronously with ack when it is successful.
     * </pre>
     */
    public void sendSync(org.opennms.integration.api.v1.proto.Model.InMemoryEvent request,
        io.grpc.stub.StreamObserver<org.opennms.integration.api.v1.proto.Events.Ack> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSendSyncMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class EventForwarderBlockingStub extends io.grpc.stub.AbstractStub<EventForwarderBlockingStub> {
    private EventForwarderBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EventForwarderBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EventForwarderBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EventForwarderBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Send the given event asynchronously.
     * </pre>
     */
    public org.opennms.integration.api.v1.proto.Model.Empty sendAsync(org.opennms.integration.api.v1.proto.Model.InMemoryEvent request) {
      return blockingUnaryCall(
          getChannel(), getSendAsyncMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Send the given event synchronously with ack when it is successful.
     * </pre>
     */
    public org.opennms.integration.api.v1.proto.Events.Ack sendSync(org.opennms.integration.api.v1.proto.Model.InMemoryEvent request) {
      return blockingUnaryCall(
          getChannel(), getSendSyncMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class EventForwarderFutureStub extends io.grpc.stub.AbstractStub<EventForwarderFutureStub> {
    private EventForwarderFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EventForwarderFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EventForwarderFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EventForwarderFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Send the given event asynchronously.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.opennms.integration.api.v1.proto.Model.Empty> sendAsync(
        org.opennms.integration.api.v1.proto.Model.InMemoryEvent request) {
      return futureUnaryCall(
          getChannel().newCall(getSendAsyncMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Send the given event synchronously with ack when it is successful.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.opennms.integration.api.v1.proto.Events.Ack> sendSync(
        org.opennms.integration.api.v1.proto.Model.InMemoryEvent request) {
      return futureUnaryCall(
          getChannel().newCall(getSendSyncMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SEND_ASYNC = 0;
  private static final int METHODID_SEND_SYNC = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final EventForwarderImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(EventForwarderImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SEND_ASYNC:
          serviceImpl.sendAsync((org.opennms.integration.api.v1.proto.Model.InMemoryEvent) request,
              (io.grpc.stub.StreamObserver<org.opennms.integration.api.v1.proto.Model.Empty>) responseObserver);
          break;
        case METHODID_SEND_SYNC:
          serviceImpl.sendSync((org.opennms.integration.api.v1.proto.Model.InMemoryEvent) request,
              (io.grpc.stub.StreamObserver<org.opennms.integration.api.v1.proto.Events.Ack>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class EventForwarderBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    EventForwarderBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.opennms.integration.api.v1.proto.Events.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("EventForwarder");
    }
  }

  private static final class EventForwarderFileDescriptorSupplier
      extends EventForwarderBaseDescriptorSupplier {
    EventForwarderFileDescriptorSupplier() {}
  }

  private static final class EventForwarderMethodDescriptorSupplier
      extends EventForwarderBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    EventForwarderMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (EventForwarderGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new EventForwarderFileDescriptorSupplier())
              .addMethod(getSendAsyncMethod())
              .addMethod(getSendSyncMethod())
              .build();
        }
      }
    }
    return result;
  }
}
