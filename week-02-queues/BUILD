java_binary(
    name = "Deque",
    srcs = glob([
        "Deque.java",
        "RandomizedQueue.java",
    ]),
    main_class = "Deque",
    deps = [
        "@algs4//jar",
    ],
)

load("//:junit5.bzl", "java_junit5_test")

java_junit5_test(
    name = "DequeTest",
    srcs = glob(["*.java"]),
    test_class = "DequeTest",
    deps = [
        "@algs4//jar",
    ],
)

java_junit5_test(
    name = "RandomizedQueueTest",
    srcs = glob(["*.java"]),
    test_class = "RandomizedQueueTest",
    deps = [
        "@algs4//jar",
    ],
)
