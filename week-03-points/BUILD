java_binary(
    name = "Point",
    srcs = glob([
        "Point.java",
        "BruteCollinearPoints.java",
        "LineSegment.java"
    ]),
    main_class = "Point",
    deps = [
        "@algs4//jar",
    ],
)

load("//:junit5.bzl", "java_junit5_test")

java_junit5_test(
    name = "PointTest",
    srcs = glob(["*.java"]),
    test_class = "PointTest",
    deps = [
        "@algs4//jar",
    ],
)

java_junit5_test(
    name = "BruteCollinearPointsTest",
    srcs = glob(["*.java"]),
    test_class = "BruteCollinearPointsTest",
    deps = [
        "@algs4//jar",
    ],
)

java_junit5_test(
    name = "FastCollinearPointsTest",
    srcs = glob(["*.java"]),
    test_class = "FastCollinearPointsTest",
    deps = [
        "@algs4//jar",
    ],
)
