java_binary(
    name = "Board",
    srcs = glob([
        "Board.java",
        "Solver.java",
    ]),
    main_class = "Solver",
    deps = [
        "@algs4//jar",
    ],
)

load("//:junit5.bzl", "java_junit5_test")

java_junit5_test(
    name = "BoardTest",
    srcs = glob(["*.java"]),
    test_class = "BoardTest",
    deps = [
        "@algs4//jar",
    ],
)

java_junit5_test(
    name = "SolverTest",
    srcs = glob(["*.java"]),
    test_class = "SolverTest",
    deps = [
        "@algs4//jar",
    ],
)
