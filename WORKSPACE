load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")
load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_jar")

RULES_JVM_EXTERNAL_TAG = "3.0"
RULES_JVM_EXTERNAL_SHA = "62133c125bf4109dfd9d2af64830208356ce4ef8b165a6ef15bbff7460b35c3a"

http_archive(
    name = "rules_jvm_external",
    strip_prefix = "rules_jvm_external-%s" % RULES_JVM_EXTERNAL_TAG,
    sha256 = RULES_JVM_EXTERNAL_SHA,
    url = "https://github.com/bazelbuild/rules_jvm_external/archive/%s.zip" % RULES_JVM_EXTERNAL_TAG,
)

load("@rules_jvm_external//:defs.bzl", "maven_install")

maven_install(
    artifacts = [
        "org.junit.jupiter:junit-jupiter-api:5.4.2",
        "org.junit.jupiter:junit-jupiter-params:5.4.2",
        "org.junit.jupiter:junit-jupiter-engine:5.4.2",
        "org.junit.platform:junit-platform-console:1.4.2",
    ],
    repositories = [
        "https://repo1.maven.org/maven2",
    ],
)

http_jar(
    name = "algs4",
    urls = ["https://algs4.cs.princeton.edu/code/algs4.jar"],
)
