package org.opennms.integration.api.v1.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.24.0)",
    comments = "Source: events.proto")
public final class EventListenerGrpc {

  private EventListenerGrpc() {}

  public static final String SERVICE_NAME = "oia.EventListener";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.opennms.integration.api.v1.proto.Events.EventListenerId,
      org.opennms.integration.api.v1.proto.Model.InMemoryEvent> getOnEventMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "OnEvent",
      requestType = org.opennms.integration.api.v1.proto.Events.EventListenerId.class,
      responseType = org.opennms.integration.api.v1.proto.Model.InMemoryEvent.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.opennms.integration.api.v1.proto.Events.EventListenerId,
      org.opennms.integration.api.v1.proto.Model.InMemoryEvent> getOnEventMethod() {
    io.grpc.MethodDescriptor<org.opennms.integration.api.v1.proto.Events.EventListenerId, org.opennms.integration.api.v1.proto.Model.InMemoryEvent> getOnEventMethod;
    if ((getOnEventMethod = EventListenerGrpc.getOnEventMethod) == null) {
      synchronized (EventListenerGrpc.class) {
        if ((getOnEventMethod = EventListenerGrpc.getOnEventMethod) == null) {
          EventListenerGrpc.getOnEventMethod = getOnEventMethod =
              io.grpc.MethodDescriptor.<org.opennms.integration.api.v1.proto.Events.EventListenerId, org.opennms.integration.api.v1.proto.Model.InMemoryEvent>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "OnEvent"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.opennms.integration.api.v1.proto.Events.EventListenerId.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.opennms.integration.api.v1.proto.Model.InMemoryEvent.getDefaultInstance()))
              .setSchemaDescriptor(new EventListenerMethodDescriptorSupplier("OnEvent"))
              .build();
        }
      }
    }
    return getOnEventMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static EventListenerStub newStub(io.grpc.Channel channel) {
    return new EventListenerStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static EventListenerBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new EventListenerBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static EventListenerFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new EventListenerFutureStub(channel);
  }

  /**
   */
  public static abstract class EventListenerImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Listen to all the events from OpenNMS.
     * </pre>
     */
    public void onEvent(org.opennms.integration.api.v1.proto.Events.EventListenerId request,
        io.grpc.stub.StreamObserver<org.opennms.integration.api.v1.proto.Model.InMemoryEvent> responseObserver) {
      asyncUnimplementedUnaryCall(getOnEventMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getOnEventMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                org.opennms.integration.api.v1.proto.Events.EventListenerId,
                org.opennms.integration.api.v1.proto.Model.InMemoryEvent>(
                  this, METHODID_ON_EVENT)))
          .build();
    }
  }

  /**
   */
  public static final class EventListenerStub extends io.grpc.stub.AbstractStub<EventListenerStub> {
    private EventListenerStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EventListenerStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EventListenerStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EventListenerStub(channel, callOptions);
    }

    /**
     * <pre>
     * Listen to all the events from OpenNMS.
     * </pre>
     */
    public void onEvent(org.opennms.integration.api.v1.proto.Events.EventListenerId request,
        io.grpc.stub.StreamObserver<org.opennms.integration.api.v1.proto.Model.InMemoryEvent> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getOnEventMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class EventListenerBlockingStub extends io.grpc.stub.AbstractStub<EventListenerBlockingStub> {
    private EventListenerBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EventListenerBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EventListenerBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EventListenerBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Listen to all the events from OpenNMS.
     * </pre>
     */
    public org.opennms.integration.api.v1.proto.Model.InMemoryEvent onEvent(org.opennms.integration.api.v1.proto.Events.EventListenerId request) {
      return blockingUnaryCall(
          getChannel(), getOnEventMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class EventListenerFutureStub extends io.grpc.stub.AbstractStub<EventListenerFutureStub> {
    private EventListenerFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EventListenerFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EventListenerFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EventListenerFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Listen to all the events from OpenNMS.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.opennms.integration.api.v1.proto.Model.InMemoryEvent> onEvent(
        org.opennms.integration.api.v1.proto.Events.EventListenerId request) {
      return futureUnaryCall(
          getChannel().newCall(getOnEventMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ON_EVENT = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final EventListenerImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(EventListenerImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ON_EVENT:
          serviceImpl.onEvent((org.opennms.integration.api.v1.proto.Events.EventListenerId) request,
              (io.grpc.stub.StreamObserver<org.opennms.integration.api.v1.proto.Model.InMemoryEvent>) responseObserver);
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

  private static abstract class EventListenerBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    EventListenerBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.opennms.integration.api.v1.proto.Events.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("EventListener");
    }
  }

  private static final class EventListenerFileDescriptorSupplier
      extends EventListenerBaseDescriptorSupplier {
    EventListenerFileDescriptorSupplier() {}
  }

  private static final class EventListenerMethodDescriptorSupplier
      extends EventListenerBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    EventListenerMethodDescriptorSupplier(String methodName) {
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
      synchronized (EventListenerGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new EventListenerFileDescriptorSupplier())
              .addMethod(getOnEventMethod())
              .build();
        }
      }
    }
    return result;
  }
}
