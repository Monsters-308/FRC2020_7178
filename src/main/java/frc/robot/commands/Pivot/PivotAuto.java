/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Pivot;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Pivot;

public class PivotAuto extends CommandBase {
  
  private final Pivot m_Pivot;
  
  public PivotAuto(Pivot subsystem) {
    m_Pivot = subsystem;
    addRequirements(m_Pivot);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_Pivot.AutoPivotUp();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(Pivot.m_shootMove.getSelectedSensorPosition() > 175) {
      return false;
    } else if(Pivot.m_shootMove.getSelectedSensorPosition() < 155) {
      return false;
    } else {
      return true;
    }
  }
}
