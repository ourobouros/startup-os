package(default_visibility = ["//visibility:public"])

java_library(
  name = "memory_repo",
  srcs = [
      "MemoryRepo.java",
  ],
  deps = [
      "//common/repo",
      "//common/repo:repo_java_proto",
      "//tools/reviewer/service:service_lib",
      "//third_party/maven/org/slf4j:slf4j_api",
      "//third_party/maven/javax/annotation:javax_annotation_api",
      "//third_party/maven/com/google/guava:guava",
  ]
)

java_binary(
    name = "test_tool",
    srcs = [
        "TestTool.java",
    ],
    main_class = "com.google.startupos.common.repo.tests.TestTool",
    deps = [
        "//common",
        "//common/repo",
        "//common:dagger_with_annotation_processor",
        "//third_party/maven/javax/inject:javax_inject",
        "//third_party/maven/org/slf4j:slf4j_simple",
    ],
)
