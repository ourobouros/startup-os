/*
 * Copyright 2018 The StartupOS Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.startupos.tools.aa;

import com.google.startupos.common.CommonModule;
import com.google.startupos.tools.aa.commands.AaCommand;
import com.google.startupos.tools.aa.commands.ConfigModule;
import com.google.startupos.tools.aa.commands.InitCommand;
import com.google.startupos.tools.aa.commands.WorkspaceCommand;
import dagger.BindsInstance;
import dagger.Component;
import java.util.HashMap;
import javax.inject.Inject;
import javax.inject.Singleton;

/** aa tool. */
@Singleton
public class AaTool {
  private HashMap<String, AaCommand> commands = new HashMap<>();
  // TODO: maybe make a flag to set it dynamically
  public static final String CONFIG_FILENAME = "~/aaconfig.prototxt";

  private void printUsage() {
    System.out.println(
        String.format(
            "Invalid usage. Available commands are: %s", String.join(", ", commands.keySet())));
  }

  @Singleton
  @Component(modules = {CommonModule.class, ConfigModule.class})
  public interface AaToolComponent {
    AaTool getAaTool();

    @Component.Builder
    interface Builder {
      @BindsInstance
      Builder configFileName(String configFileName);

      AaToolComponent build();
    }
  }

  @Inject
  AaTool(InitCommand initCommand, WorkspaceCommand workspaceCommand) {
    commands.put(initCommand.getName(), initCommand);
    commands.put(workspaceCommand.getName(), workspaceCommand);
  }

  private void run(String[] args) {
    if (args.length > 0) {
      String command = args[0];
      if (commands.containsKey(command)) {
        commands.get(command).run(args);
      } else {
        System.out.println("");
        printUsage();
      }
    } else {
      printUsage();
    }
  }

  public static void main(String[] args) {
    DaggerAaTool_AaToolComponent.builder()
        .configFileName(CONFIG_FILENAME)
        .build()
        .getAaTool()
        .run(args);
  }
}
