// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto.AutoBall;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Shooter.ShooterCommand;
import frc.robot.commands.Turret.TurretMiddle.MiddleCommand;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.TriggerSubsystem;
import frc.robot.subsystems.TunnelSubsystem;
import frc.robot.subsystems.TurretSubsystem;
import frc.robot.subsystems.VisionSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoVisionBall extends SequentialCommandGroup {
  /** Creates a new AutoVisionBall. */
  public AutoVisionBall(TurretSubsystem turret, VisionSubsystem vision, ShooterSubsystem shooter, TriggerSubsystem trigger, TunnelSubsystem tunnel, double shooterSpeed, double tunnelTriggerSpeed) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new MiddleCommand(turret, vision).withTimeout(1).alongWith(new ShooterCommand(shooter, () -> -1)).withTimeout(4),
      new ParallelCommandGroup(
        new RunCommand(() -> trigger.runTrigger(tunnelTriggerSpeed), trigger),
        new RunCommand(() -> tunnel.runTunnel(tunnelTriggerSpeed), tunnel),
        new RunCommand(() -> shooter.runShooter(shooterSpeed), shooter)
      ){
        @Override
        public void end(boolean interrupted){
          trigger.runTrigger(0);
          tunnel.runTunnel(0);
          shooter.runShooter(0);
        }
      }.withTimeout(2)
    );
  }
}
