java_binary(
    name = "PointSET",
    srcs = glob([
        "PointSET.java",
        "KdTree.java",
    ]),
    main_class = "Solver",
    deps = [
        "@algs4//jar",
    ],
)

load("//:junit5.bzl", "java_junit5_test")

java_junit5_test(
    name = "PointSETTest",
    srcs = glob(["*.java"]),
    test_class = "PointSETTest",
    deps = [
        "@algs4//jar",
    ],
)

java_junit5_test(
    name = "KdTreeTest",
    srcs = glob(["*.java"]),
    test_class = "KdTreeTest",
    deps = [
        "@algs4//jar",
    ],
)
