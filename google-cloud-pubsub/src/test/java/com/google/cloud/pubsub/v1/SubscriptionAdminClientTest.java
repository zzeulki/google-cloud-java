/*
 * Copyright 2017, Google Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.cloud.pubsub.v1;

import static com.google.cloud.pubsub.v1.PagedResponseWrappers.ListSnapshotsPagedResponse;
import static com.google.cloud.pubsub.v1.PagedResponseWrappers.ListSubscriptionsPagedResponse;

import com.google.api.gax.core.NoCredentialsProvider;
import com.google.api.gax.grpc.GrpcApiException;
import com.google.api.gax.grpc.GrpcTransportProvider;
import com.google.api.gax.grpc.testing.MockGrpcService;
import com.google.api.gax.grpc.testing.MockServiceHelper;
import com.google.api.gax.grpc.testing.MockStreamObserver;
import com.google.api.gax.rpc.ApiStreamObserver;
import com.google.api.gax.rpc.StreamingCallable;
import com.google.common.collect.Lists;
import com.google.iam.v1.GetIamPolicyRequest;
import com.google.iam.v1.Policy;
import com.google.iam.v1.SetIamPolicyRequest;
import com.google.iam.v1.TestIamPermissionsRequest;
import com.google.iam.v1.TestIamPermissionsResponse;
import com.google.protobuf.ByteString;
import com.google.protobuf.Empty;
import com.google.protobuf.GeneratedMessageV3;
import com.google.pubsub.v1.AcknowledgeRequest;
import com.google.pubsub.v1.CreateSnapshotRequest;
import com.google.pubsub.v1.DeleteSnapshotRequest;
import com.google.pubsub.v1.DeleteSubscriptionRequest;
import com.google.pubsub.v1.GetSubscriptionRequest;
import com.google.pubsub.v1.ListSnapshotsRequest;
import com.google.pubsub.v1.ListSnapshotsResponse;
import com.google.pubsub.v1.ListSubscriptionsRequest;
import com.google.pubsub.v1.ListSubscriptionsResponse;
import com.google.pubsub.v1.ModifyAckDeadlineRequest;
import com.google.pubsub.v1.ModifyPushConfigRequest;
import com.google.pubsub.v1.ProjectName;
import com.google.pubsub.v1.PullRequest;
import com.google.pubsub.v1.PullResponse;
import com.google.pubsub.v1.PushConfig;
import com.google.pubsub.v1.ReceivedMessage;
import com.google.pubsub.v1.Snapshot;
import com.google.pubsub.v1.SnapshotName;
import com.google.pubsub.v1.StreamingPullRequest;
import com.google.pubsub.v1.StreamingPullResponse;
import com.google.pubsub.v1.Subscription;
import com.google.pubsub.v1.SubscriptionName;
import com.google.pubsub.v1.TopicName;
import com.google.pubsub.v1.TopicNameOneof;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

@javax.annotation.Generated("by GAPIC")
public class SubscriptionAdminClientTest {
  private static MockPublisher mockPublisher;
  private static MockIAMPolicy mockIAMPolicy;
  private static MockSubscriber mockSubscriber;
  private static MockServiceHelper serviceHelper;
  private SubscriptionAdminClient client;

  @BeforeClass
  public static void startStaticServer() {
    mockPublisher = new MockPublisher();
    mockIAMPolicy = new MockIAMPolicy();
    mockSubscriber = new MockSubscriber();
    serviceHelper =
        new MockServiceHelper(
            "in-process-1",
            Arrays.<MockGrpcService>asList(mockPublisher, mockIAMPolicy, mockSubscriber));
    serviceHelper.start();
  }

  @AfterClass
  public static void stopServer() {
    serviceHelper.stop();
  }

  @Before
  public void setUp() throws IOException {
    serviceHelper.reset();
    SubscriptionAdminSettings settings =
        SubscriptionAdminSettings.defaultBuilder()
            .setTransportProvider(
                GrpcTransportProvider.newBuilder()
                    .setChannelProvider(serviceHelper.createChannelProvider())
                    .build())
            .setCredentialsProvider(new NoCredentialsProvider())
            .build();
    client = SubscriptionAdminClient.create(settings);
  }

  @After
  public void tearDown() throws Exception {
    client.close();
  }

  @Test
  @SuppressWarnings("all")
  public void createSubscriptionTest() {
    SubscriptionName name2 = SubscriptionName.create("[PROJECT]", "[SUBSCRIPTION]");
    TopicNameOneof topic2 = TopicNameOneof.from(TopicName.create("[PROJECT]", "[TOPIC]"));
    int ackDeadlineSeconds2 = -921632575;
    boolean retainAckedMessages = false;
    Subscription expectedResponse =
        Subscription.newBuilder()
            .setNameWithSubscriptionName(name2)
            .setTopicWithTopicNameOneof(topic2)
            .setAckDeadlineSeconds(ackDeadlineSeconds2)
            .setRetainAckedMessages(retainAckedMessages)
            .build();
    mockSubscriber.addResponse(expectedResponse);

    SubscriptionName name = SubscriptionName.create("[PROJECT]", "[SUBSCRIPTION]");
    TopicName topic = TopicName.create("[PROJECT]", "[TOPIC]");
    PushConfig pushConfig = PushConfig.newBuilder().build();
    int ackDeadlineSeconds = 2135351438;

    Subscription actualResponse =
        client.createSubscription(name, topic, pushConfig, ackDeadlineSeconds);
    Assert.assertEquals(expectedResponse, actualResponse);

    List<GeneratedMessageV3> actualRequests = mockSubscriber.getRequests();
    Assert.assertEquals(1, actualRequests.size());
    Subscription actualRequest = (Subscription) actualRequests.get(0);

    Assert.assertEquals(name, actualRequest.getNameAsSubscriptionName());
    Assert.assertEquals(TopicNameOneof.from(topic), actualRequest.getTopicAsTopicNameOneof());
    Assert.assertEquals(pushConfig, actualRequest.getPushConfig());
    Assert.assertEquals(ackDeadlineSeconds, actualRequest.getAckDeadlineSeconds());
  }

  @Test
  @SuppressWarnings("all")
  public void createSubscriptionExceptionTest() throws Exception {
    StatusRuntimeException exception = new StatusRuntimeException(Status.INVALID_ARGUMENT);
    mockSubscriber.addException(exception);

    try {
      SubscriptionName name = SubscriptionName.create("[PROJECT]", "[SUBSCRIPTION]");
      TopicName topic = TopicName.create("[PROJECT]", "[TOPIC]");
      PushConfig pushConfig = PushConfig.newBuilder().build();
      int ackDeadlineSeconds = 2135351438;

      client.createSubscription(name, topic, pushConfig, ackDeadlineSeconds);
      Assert.fail("No exception raised");
    } catch (GrpcApiException e) {
      Assert.assertEquals(Status.INVALID_ARGUMENT.getCode(), e.getStatusCode().getCode());
    }
  }

  @Test
  @SuppressWarnings("all")
  public void getSubscriptionTest() {
    SubscriptionName name = SubscriptionName.create("[PROJECT]", "[SUBSCRIPTION]");
    TopicNameOneof topic = TopicNameOneof.from(TopicName.create("[PROJECT]", "[TOPIC]"));
    int ackDeadlineSeconds = 2135351438;
    boolean retainAckedMessages = false;
    Subscription expectedResponse =
        Subscription.newBuilder()
            .setNameWithSubscriptionName(name)
            .setTopicWithTopicNameOneof(topic)
            .setAckDeadlineSeconds(ackDeadlineSeconds)
            .setRetainAckedMessages(retainAckedMessages)
            .build();
    mockSubscriber.addResponse(expectedResponse);

    SubscriptionName subscription = SubscriptionName.create("[PROJECT]", "[SUBSCRIPTION]");

    Subscription actualResponse = client.getSubscription(subscription);
    Assert.assertEquals(expectedResponse, actualResponse);

    List<GeneratedMessageV3> actualRequests = mockSubscriber.getRequests();
    Assert.assertEquals(1, actualRequests.size());
    GetSubscriptionRequest actualRequest = (GetSubscriptionRequest) actualRequests.get(0);

    Assert.assertEquals(subscription, actualRequest.getSubscriptionAsSubscriptionName());
  }

  @Test
  @SuppressWarnings("all")
  public void getSubscriptionExceptionTest() throws Exception {
    StatusRuntimeException exception = new StatusRuntimeException(Status.INVALID_ARGUMENT);
    mockSubscriber.addException(exception);

    try {
      SubscriptionName subscription = SubscriptionName.create("[PROJECT]", "[SUBSCRIPTION]");

      client.getSubscription(subscription);
      Assert.fail("No exception raised");
    } catch (GrpcApiException e) {
      Assert.assertEquals(Status.INVALID_ARGUMENT.getCode(), e.getStatusCode().getCode());
    }
  }

  @Test
  @SuppressWarnings("all")
  public void listSubscriptionsTest() {
    String nextPageToken = "";
    Subscription subscriptionsElement = Subscription.newBuilder().build();
    List<Subscription> subscriptions = Arrays.asList(subscriptionsElement);
    ListSubscriptionsResponse expectedResponse =
        ListSubscriptionsResponse.newBuilder()
            .setNextPageToken(nextPageToken)
            .addAllSubscriptions(subscriptions)
            .build();
    mockSubscriber.addResponse(expectedResponse);

    ProjectName project = ProjectName.create("[PROJECT]");

    ListSubscriptionsPagedResponse pagedListResponse = client.listSubscriptions(project);

    List<Subscription> resources = Lists.newArrayList(pagedListResponse.iterateAll());
    Assert.assertEquals(1, resources.size());
    Assert.assertEquals(expectedResponse.getSubscriptionsList().get(0), resources.get(0));

    List<GeneratedMessageV3> actualRequests = mockSubscriber.getRequests();
    Assert.assertEquals(1, actualRequests.size());
    ListSubscriptionsRequest actualRequest = (ListSubscriptionsRequest) actualRequests.get(0);

    Assert.assertEquals(project, actualRequest.getProjectAsProjectName());
  }

  @Test
  @SuppressWarnings("all")
  public void listSubscriptionsExceptionTest() throws Exception {
    StatusRuntimeException exception = new StatusRuntimeException(Status.INVALID_ARGUMENT);
    mockSubscriber.addException(exception);

    try {
      ProjectName project = ProjectName.create("[PROJECT]");

      client.listSubscriptions(project);
      Assert.fail("No exception raised");
    } catch (GrpcApiException e) {
      Assert.assertEquals(Status.INVALID_ARGUMENT.getCode(), e.getStatusCode().getCode());
    }
  }

  @Test
  @SuppressWarnings("all")
  public void deleteSubscriptionTest() {
    Empty expectedResponse = Empty.newBuilder().build();
    mockSubscriber.addResponse(expectedResponse);

    SubscriptionName subscription = SubscriptionName.create("[PROJECT]", "[SUBSCRIPTION]");

    client.deleteSubscription(subscription);

    List<GeneratedMessageV3> actualRequests = mockSubscriber.getRequests();
    Assert.assertEquals(1, actualRequests.size());
    DeleteSubscriptionRequest actualRequest = (DeleteSubscriptionRequest) actualRequests.get(0);

    Assert.assertEquals(subscription, actualRequest.getSubscriptionAsSubscriptionName());
  }

  @Test
  @SuppressWarnings("all")
  public void deleteSubscriptionExceptionTest() throws Exception {
    StatusRuntimeException exception = new StatusRuntimeException(Status.INVALID_ARGUMENT);
    mockSubscriber.addException(exception);

    try {
      SubscriptionName subscription = SubscriptionName.create("[PROJECT]", "[SUBSCRIPTION]");

      client.deleteSubscription(subscription);
      Assert.fail("No exception raised");
    } catch (GrpcApiException e) {
      Assert.assertEquals(Status.INVALID_ARGUMENT.getCode(), e.getStatusCode().getCode());
    }
  }

  @Test
  @SuppressWarnings("all")
  public void modifyAckDeadlineTest() {
    Empty expectedResponse = Empty.newBuilder().build();
    mockSubscriber.addResponse(expectedResponse);

    SubscriptionName subscription = SubscriptionName.create("[PROJECT]", "[SUBSCRIPTION]");
    List<String> ackIds = new ArrayList<>();
    int ackDeadlineSeconds = 2135351438;

    client.modifyAckDeadline(subscription, ackIds, ackDeadlineSeconds);

    List<GeneratedMessageV3> actualRequests = mockSubscriber.getRequests();
    Assert.assertEquals(1, actualRequests.size());
    ModifyAckDeadlineRequest actualRequest = (ModifyAckDeadlineRequest) actualRequests.get(0);

    Assert.assertEquals(subscription, actualRequest.getSubscriptionAsSubscriptionName());
    Assert.assertEquals(ackIds, actualRequest.getAckIdsList());
    Assert.assertEquals(ackDeadlineSeconds, actualRequest.getAckDeadlineSeconds());
  }

  @Test
  @SuppressWarnings("all")
  public void modifyAckDeadlineExceptionTest() throws Exception {
    StatusRuntimeException exception = new StatusRuntimeException(Status.INVALID_ARGUMENT);
    mockSubscriber.addException(exception);

    try {
      SubscriptionName subscription = SubscriptionName.create("[PROJECT]", "[SUBSCRIPTION]");
      List<String> ackIds = new ArrayList<>();
      int ackDeadlineSeconds = 2135351438;

      client.modifyAckDeadline(subscription, ackIds, ackDeadlineSeconds);
      Assert.fail("No exception raised");
    } catch (GrpcApiException e) {
      Assert.assertEquals(Status.INVALID_ARGUMENT.getCode(), e.getStatusCode().getCode());
    }
  }

  @Test
  @SuppressWarnings("all")
  public void acknowledgeTest() {
    Empty expectedResponse = Empty.newBuilder().build();
    mockSubscriber.addResponse(expectedResponse);

    SubscriptionName subscription = SubscriptionName.create("[PROJECT]", "[SUBSCRIPTION]");
    List<String> ackIds = new ArrayList<>();

    client.acknowledge(subscription, ackIds);

    List<GeneratedMessageV3> actualRequests = mockSubscriber.getRequests();
    Assert.assertEquals(1, actualRequests.size());
    AcknowledgeRequest actualRequest = (AcknowledgeRequest) actualRequests.get(0);

    Assert.assertEquals(subscription, actualRequest.getSubscriptionAsSubscriptionName());
    Assert.assertEquals(ackIds, actualRequest.getAckIdsList());
  }

  @Test
  @SuppressWarnings("all")
  public void acknowledgeExceptionTest() throws Exception {
    StatusRuntimeException exception = new StatusRuntimeException(Status.INVALID_ARGUMENT);
    mockSubscriber.addException(exception);

    try {
      SubscriptionName subscription = SubscriptionName.create("[PROJECT]", "[SUBSCRIPTION]");
      List<String> ackIds = new ArrayList<>();

      client.acknowledge(subscription, ackIds);
      Assert.fail("No exception raised");
    } catch (GrpcApiException e) {
      Assert.assertEquals(Status.INVALID_ARGUMENT.getCode(), e.getStatusCode().getCode());
    }
  }

  @Test
  @SuppressWarnings("all")
  public void pullTest() {
    PullResponse expectedResponse = PullResponse.newBuilder().build();
    mockSubscriber.addResponse(expectedResponse);

    SubscriptionName subscription = SubscriptionName.create("[PROJECT]", "[SUBSCRIPTION]");
    boolean returnImmediately = false;
    int maxMessages = 496131527;

    PullResponse actualResponse = client.pull(subscription, returnImmediately, maxMessages);
    Assert.assertEquals(expectedResponse, actualResponse);

    List<GeneratedMessageV3> actualRequests = mockSubscriber.getRequests();
    Assert.assertEquals(1, actualRequests.size());
    PullRequest actualRequest = (PullRequest) actualRequests.get(0);

    Assert.assertEquals(subscription, actualRequest.getSubscriptionAsSubscriptionName());
    Assert.assertEquals(returnImmediately, actualRequest.getReturnImmediately());
    Assert.assertEquals(maxMessages, actualRequest.getMaxMessages());
  }

  @Test
  @SuppressWarnings("all")
  public void pullExceptionTest() throws Exception {
    StatusRuntimeException exception = new StatusRuntimeException(Status.INVALID_ARGUMENT);
    mockSubscriber.addException(exception);

    try {
      SubscriptionName subscription = SubscriptionName.create("[PROJECT]", "[SUBSCRIPTION]");
      boolean returnImmediately = false;
      int maxMessages = 496131527;

      client.pull(subscription, returnImmediately, maxMessages);
      Assert.fail("No exception raised");
    } catch (GrpcApiException e) {
      Assert.assertEquals(Status.INVALID_ARGUMENT.getCode(), e.getStatusCode().getCode());
    }
  }

  @Test
  @SuppressWarnings("all")
  public void streamingPullTest() throws Exception {
    ReceivedMessage receivedMessagesElement = ReceivedMessage.newBuilder().build();
    List<ReceivedMessage> receivedMessages = Arrays.asList(receivedMessagesElement);
    StreamingPullResponse expectedResponse =
        StreamingPullResponse.newBuilder().addAllReceivedMessages(receivedMessages).build();
    mockSubscriber.addResponse(expectedResponse);
    SubscriptionName subscription = SubscriptionName.create("[PROJECT]", "[SUBSCRIPTION]");
    int streamAckDeadlineSeconds = 1875467245;
    StreamingPullRequest request =
        StreamingPullRequest.newBuilder()
            .setSubscriptionWithSubscriptionName(subscription)
            .setStreamAckDeadlineSeconds(streamAckDeadlineSeconds)
            .build();

    MockStreamObserver<StreamingPullResponse> responseObserver = new MockStreamObserver<>();

    StreamingCallable<StreamingPullRequest, StreamingPullResponse> callable =
        client.streamingPullCallable();
    ApiStreamObserver<StreamingPullRequest> requestObserver =
        callable.bidiStreamingCall(responseObserver);

    requestObserver.onNext(request);
    requestObserver.onCompleted();

    List<StreamingPullResponse> actualResponses = responseObserver.future().get();
    Assert.assertEquals(1, actualResponses.size());
    Assert.assertEquals(expectedResponse, actualResponses.get(0));
  }

  @Test
  @SuppressWarnings("all")
  public void streamingPullExceptionTest() throws Exception {
    StatusRuntimeException exception = new StatusRuntimeException(Status.INVALID_ARGUMENT);
    mockSubscriber.addException(exception);
    SubscriptionName subscription = SubscriptionName.create("[PROJECT]", "[SUBSCRIPTION]");
    int streamAckDeadlineSeconds = 1875467245;
    StreamingPullRequest request =
        StreamingPullRequest.newBuilder()
            .setSubscriptionWithSubscriptionName(subscription)
            .setStreamAckDeadlineSeconds(streamAckDeadlineSeconds)
            .build();

    MockStreamObserver<StreamingPullResponse> responseObserver = new MockStreamObserver<>();

    StreamingCallable<StreamingPullRequest, StreamingPullResponse> callable =
        client.streamingPullCallable();
    ApiStreamObserver<StreamingPullRequest> requestObserver =
        callable.bidiStreamingCall(responseObserver);

    requestObserver.onNext(request);

    try {
      List<StreamingPullResponse> actualResponses = responseObserver.future().get();
      Assert.fail("No exception thrown");
    } catch (ExecutionException e) {
      Assert.assertTrue(e.getCause() instanceof StatusRuntimeException);
      StatusRuntimeException statusException = (StatusRuntimeException) e.getCause();
      Assert.assertEquals(Status.INVALID_ARGUMENT, statusException.getStatus());
    }
  }

  @Test
  @SuppressWarnings("all")
  public void modifyPushConfigTest() {
    Empty expectedResponse = Empty.newBuilder().build();
    mockSubscriber.addResponse(expectedResponse);

    SubscriptionName subscription = SubscriptionName.create("[PROJECT]", "[SUBSCRIPTION]");
    PushConfig pushConfig = PushConfig.newBuilder().build();

    client.modifyPushConfig(subscription, pushConfig);

    List<GeneratedMessageV3> actualRequests = mockSubscriber.getRequests();
    Assert.assertEquals(1, actualRequests.size());
    ModifyPushConfigRequest actualRequest = (ModifyPushConfigRequest) actualRequests.get(0);

    Assert.assertEquals(subscription, actualRequest.getSubscriptionAsSubscriptionName());
    Assert.assertEquals(pushConfig, actualRequest.getPushConfig());
  }

  @Test
  @SuppressWarnings("all")
  public void modifyPushConfigExceptionTest() throws Exception {
    StatusRuntimeException exception = new StatusRuntimeException(Status.INVALID_ARGUMENT);
    mockSubscriber.addException(exception);

    try {
      SubscriptionName subscription = SubscriptionName.create("[PROJECT]", "[SUBSCRIPTION]");
      PushConfig pushConfig = PushConfig.newBuilder().build();

      client.modifyPushConfig(subscription, pushConfig);
      Assert.fail("No exception raised");
    } catch (GrpcApiException e) {
      Assert.assertEquals(Status.INVALID_ARGUMENT.getCode(), e.getStatusCode().getCode());
    }
  }

  @Test
  @SuppressWarnings("all")
  public void listSnapshotsTest() {
    String nextPageToken = "";
    Snapshot snapshotsElement = Snapshot.newBuilder().build();
    List<Snapshot> snapshots = Arrays.asList(snapshotsElement);
    ListSnapshotsResponse expectedResponse =
        ListSnapshotsResponse.newBuilder()
            .setNextPageToken(nextPageToken)
            .addAllSnapshots(snapshots)
            .build();
    mockSubscriber.addResponse(expectedResponse);

    ProjectName project = ProjectName.create("[PROJECT]");

    ListSnapshotsPagedResponse pagedListResponse = client.listSnapshots(project);

    List<Snapshot> resources = Lists.newArrayList(pagedListResponse.iterateAll());
    Assert.assertEquals(1, resources.size());
    Assert.assertEquals(expectedResponse.getSnapshotsList().get(0), resources.get(0));

    List<GeneratedMessageV3> actualRequests = mockSubscriber.getRequests();
    Assert.assertEquals(1, actualRequests.size());
    ListSnapshotsRequest actualRequest = (ListSnapshotsRequest) actualRequests.get(0);

    Assert.assertEquals(project, actualRequest.getProjectAsProjectName());
  }

  @Test
  @SuppressWarnings("all")
  public void listSnapshotsExceptionTest() throws Exception {
    StatusRuntimeException exception = new StatusRuntimeException(Status.INVALID_ARGUMENT);
    mockSubscriber.addException(exception);

    try {
      ProjectName project = ProjectName.create("[PROJECT]");

      client.listSnapshots(project);
      Assert.fail("No exception raised");
    } catch (GrpcApiException e) {
      Assert.assertEquals(Status.INVALID_ARGUMENT.getCode(), e.getStatusCode().getCode());
    }
  }

  @Test
  @SuppressWarnings("all")
  public void createSnapshotTest() {
    SnapshotName name2 = SnapshotName.create("[PROJECT]", "[SNAPSHOT]");
    TopicName topic = TopicName.create("[PROJECT]", "[TOPIC]");
    Snapshot expectedResponse =
        Snapshot.newBuilder().setNameWithSnapshotName(name2).setTopicWithTopicName(topic).build();
    mockSubscriber.addResponse(expectedResponse);

    SnapshotName name = SnapshotName.create("[PROJECT]", "[SNAPSHOT]");
    SubscriptionName subscription = SubscriptionName.create("[PROJECT]", "[SUBSCRIPTION]");

    Snapshot actualResponse = client.createSnapshot(name, subscription);
    Assert.assertEquals(expectedResponse, actualResponse);

    List<GeneratedMessageV3> actualRequests = mockSubscriber.getRequests();
    Assert.assertEquals(1, actualRequests.size());
    CreateSnapshotRequest actualRequest = (CreateSnapshotRequest) actualRequests.get(0);

    Assert.assertEquals(name, actualRequest.getNameAsSnapshotName());
    Assert.assertEquals(subscription, actualRequest.getSubscriptionAsSubscriptionName());
  }

  @Test
  @SuppressWarnings("all")
  public void createSnapshotExceptionTest() throws Exception {
    StatusRuntimeException exception = new StatusRuntimeException(Status.INVALID_ARGUMENT);
    mockSubscriber.addException(exception);

    try {
      SnapshotName name = SnapshotName.create("[PROJECT]", "[SNAPSHOT]");
      SubscriptionName subscription = SubscriptionName.create("[PROJECT]", "[SUBSCRIPTION]");

      client.createSnapshot(name, subscription);
      Assert.fail("No exception raised");
    } catch (GrpcApiException e) {
      Assert.assertEquals(Status.INVALID_ARGUMENT.getCode(), e.getStatusCode().getCode());
    }
  }

  @Test
  @SuppressWarnings("all")
  public void deleteSnapshotTest() {
    Empty expectedResponse = Empty.newBuilder().build();
    mockSubscriber.addResponse(expectedResponse);

    SnapshotName snapshot = SnapshotName.create("[PROJECT]", "[SNAPSHOT]");

    client.deleteSnapshot(snapshot);

    List<GeneratedMessageV3> actualRequests = mockSubscriber.getRequests();
    Assert.assertEquals(1, actualRequests.size());
    DeleteSnapshotRequest actualRequest = (DeleteSnapshotRequest) actualRequests.get(0);

    Assert.assertEquals(snapshot, actualRequest.getSnapshotAsSnapshotName());
  }

  @Test
  @SuppressWarnings("all")
  public void deleteSnapshotExceptionTest() throws Exception {
    StatusRuntimeException exception = new StatusRuntimeException(Status.INVALID_ARGUMENT);
    mockSubscriber.addException(exception);

    try {
      SnapshotName snapshot = SnapshotName.create("[PROJECT]", "[SNAPSHOT]");

      client.deleteSnapshot(snapshot);
      Assert.fail("No exception raised");
    } catch (GrpcApiException e) {
      Assert.assertEquals(Status.INVALID_ARGUMENT.getCode(), e.getStatusCode().getCode());
    }
  }

  @Test
  @SuppressWarnings("all")
  public void setIamPolicyTest() {
    int version = 351608024;
    ByteString etag = ByteString.copyFromUtf8("21");
    Policy expectedResponse = Policy.newBuilder().setVersion(version).setEtag(etag).build();
    mockIAMPolicy.addResponse(expectedResponse);

    String formattedResource = SubscriptionName.create("[PROJECT]", "[SUBSCRIPTION]").toString();
    Policy policy = Policy.newBuilder().build();

    Policy actualResponse = client.setIamPolicy(formattedResource, policy);
    Assert.assertEquals(expectedResponse, actualResponse);

    List<GeneratedMessageV3> actualRequests = mockIAMPolicy.getRequests();
    Assert.assertEquals(1, actualRequests.size());
    SetIamPolicyRequest actualRequest = (SetIamPolicyRequest) actualRequests.get(0);

    Assert.assertEquals(formattedResource, actualRequest.getResource());
    Assert.assertEquals(policy, actualRequest.getPolicy());
  }

  @Test
  @SuppressWarnings("all")
  public void setIamPolicyExceptionTest() throws Exception {
    StatusRuntimeException exception = new StatusRuntimeException(Status.INVALID_ARGUMENT);
    mockIAMPolicy.addException(exception);

    try {
      String formattedResource = SubscriptionName.create("[PROJECT]", "[SUBSCRIPTION]").toString();
      Policy policy = Policy.newBuilder().build();

      client.setIamPolicy(formattedResource, policy);
      Assert.fail("No exception raised");
    } catch (GrpcApiException e) {
      Assert.assertEquals(Status.INVALID_ARGUMENT.getCode(), e.getStatusCode().getCode());
    }
  }

  @Test
  @SuppressWarnings("all")
  public void getIamPolicyTest() {
    int version = 351608024;
    ByteString etag = ByteString.copyFromUtf8("21");
    Policy expectedResponse = Policy.newBuilder().setVersion(version).setEtag(etag).build();
    mockIAMPolicy.addResponse(expectedResponse);

    String formattedResource = SubscriptionName.create("[PROJECT]", "[SUBSCRIPTION]").toString();

    Policy actualResponse = client.getIamPolicy(formattedResource);
    Assert.assertEquals(expectedResponse, actualResponse);

    List<GeneratedMessageV3> actualRequests = mockIAMPolicy.getRequests();
    Assert.assertEquals(1, actualRequests.size());
    GetIamPolicyRequest actualRequest = (GetIamPolicyRequest) actualRequests.get(0);

    Assert.assertEquals(formattedResource, actualRequest.getResource());
  }

  @Test
  @SuppressWarnings("all")
  public void getIamPolicyExceptionTest() throws Exception {
    StatusRuntimeException exception = new StatusRuntimeException(Status.INVALID_ARGUMENT);
    mockIAMPolicy.addException(exception);

    try {
      String formattedResource = SubscriptionName.create("[PROJECT]", "[SUBSCRIPTION]").toString();

      client.getIamPolicy(formattedResource);
      Assert.fail("No exception raised");
    } catch (GrpcApiException e) {
      Assert.assertEquals(Status.INVALID_ARGUMENT.getCode(), e.getStatusCode().getCode());
    }
  }

  @Test
  @SuppressWarnings("all")
  public void testIamPermissionsTest() {
    TestIamPermissionsResponse expectedResponse = TestIamPermissionsResponse.newBuilder().build();
    mockIAMPolicy.addResponse(expectedResponse);

    String formattedResource = SubscriptionName.create("[PROJECT]", "[SUBSCRIPTION]").toString();
    List<String> permissions = new ArrayList<>();

    TestIamPermissionsResponse actualResponse =
        client.testIamPermissions(formattedResource, permissions);
    Assert.assertEquals(expectedResponse, actualResponse);

    List<GeneratedMessageV3> actualRequests = mockIAMPolicy.getRequests();
    Assert.assertEquals(1, actualRequests.size());
    TestIamPermissionsRequest actualRequest = (TestIamPermissionsRequest) actualRequests.get(0);

    Assert.assertEquals(formattedResource, actualRequest.getResource());
    Assert.assertEquals(permissions, actualRequest.getPermissionsList());
  }

  @Test
  @SuppressWarnings("all")
  public void testIamPermissionsExceptionTest() throws Exception {
    StatusRuntimeException exception = new StatusRuntimeException(Status.INVALID_ARGUMENT);
    mockIAMPolicy.addException(exception);

    try {
      String formattedResource = SubscriptionName.create("[PROJECT]", "[SUBSCRIPTION]").toString();
      List<String> permissions = new ArrayList<>();

      client.testIamPermissions(formattedResource, permissions);
      Assert.fail("No exception raised");
    } catch (GrpcApiException e) {
      Assert.assertEquals(Status.INVALID_ARGUMENT.getCode(), e.getStatusCode().getCode());
    }
  }
}
