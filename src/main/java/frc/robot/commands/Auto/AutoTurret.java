// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Turret.TurretMiddleCommand;
import frc.robot.commands.Turret.TurretResetCommand;
import frc.robot.commands.Turret.TurretScanCommand;
import frc.robot.subsystems.TurretSubsystem;
import frc.robot.subsystems.VisionSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoTurret extends SequentialCommandGroup {
  /** Creates a new AutoTurret. */
  public AutoTurret(TurretSubsystem turret, VisionSubsystem vision) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new TurretResetCommand(turret),
      new TurretScanCommand(turret, vision),
      new TurretMiddleCommand(turret, vision)
    );
  }
}
