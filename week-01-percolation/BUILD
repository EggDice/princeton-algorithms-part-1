java_binary(
    name = "InteractivePercolationVisualizer",
    srcs = glob([
        "PercolationStats.java",
        "Percolation.java",
        "InteractivePercolationVisualizer.java",
        "PercolationVisualizer.java",
    ]),
    main_class = "InteractivePercolationVisualizer",
    deps = [
        "@algs4//jar",
    ],
)

java_binary(
    name = "PercolationVisualizer",
    srcs = glob([
        "PercolationStats.java",
        "Percolation.java",
        "InteractivePercolationVisualizer.java",
        "PercolationVisualizer.java",
    ]),
    main_class = "PercolationVisualizer",
    deps = [
        "@algs4//jar",
    ],
)

java_binary(
    name = "PercolationStats",
    srcs = glob([
        "PercolationStats.java",
        "Percolation.java",
    ]),
    main_class = "PercolationStats",
    deps = [
        "@algs4//jar",
    ],
)

load("//:junit5.bzl", "java_junit5_test")

java_junit5_test(
    name = "junit5-test",
    srcs = glob(["*.java"]),
    test_class = "PercolationTest",
    deps = [
        "@algs4//jar",
    ],
)
