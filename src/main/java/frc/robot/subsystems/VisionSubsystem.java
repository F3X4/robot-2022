// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.photonvision.PhotonCamera;

public class VisionSubsystem extends SubsystemBase {
  /** Creates a new VisionSubsystem. */
  final PhotonCamera camera;
  private final NetworkTableInstance NTmain;
  private final NetworkTable nt;

  public VisionSubsystem() {
    camera = new PhotonCamera("borusancam");
    NTmain = NetworkTableInstance.getDefault();
    nt = NTmain.getTable("photonvision").getSubTable("borusancam");
  }
  
  public double getX(){
    return nt.getEntry("targetYaw").getDouble(Double.NaN);
  }

  public double getY(){
    return nt.getEntry("targetPitch").getDouble(Double.NaN);
  }

  public boolean hasTarget(){
    return nt.getEntry("hasTarget").getBoolean(false);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("X Coordinate", getX());
    SmartDashboard.putNumber("Y Coordinate", getY());
  }
}
