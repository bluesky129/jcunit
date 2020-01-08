package com.github.dakusui.jcunit.core.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.stream.Stream;

public enum IoUtils {
  ;

  public static Stream<String> streamFile(File in) {
    try {
      return Files.lines(in.toPath(), StandardCharsets.UTF_8);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static void writeTo(File file, String data) {
    try {
      FileWriter writer = new FileWriter(file);
      writer.append(data);
      writer.flush();
      writer.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
