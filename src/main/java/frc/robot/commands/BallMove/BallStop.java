/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.BallMove;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.BallMover;

public class BallStop extends CommandBase {
  
  private final BallMover m_ballMover;

  public BallStop(BallMover subsystem) {
    m_ballMover = subsystem;
    addRequirements(m_ballMover);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_ballMover.ballStop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}