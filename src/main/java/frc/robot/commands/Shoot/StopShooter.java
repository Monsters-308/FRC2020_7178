/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Shoot;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shoot;

public class StopShooter extends CommandBase {
  
  private final Shoot m_Shoot;
  public StopShooter(Shoot subsystem) {
    m_Shoot = subsystem;
    addRequirements(m_Shoot);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_Shoot.stopShooter();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
