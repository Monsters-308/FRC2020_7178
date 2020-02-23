/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import frc.robot.commands.*;
import frc.robot.commands.Arm.LiftArmUp;
import frc.robot.commands.Arm.ArmDown;
import frc.robot.commands.Arm.ArmStop;
import frc.robot.commands.BallMove.BallBackward;
import frc.robot.commands.BallMove.BallForward;
import frc.robot.commands.BallMove.BallStop;
import frc.robot.commands.Intake.BallUp;
import frc.robot.commands.Intake.DeployPickup;
import frc.robot.commands.Intake.RetractPickup;
import frc.robot.commands.Intake.StopPickup;
import frc.robot.commands.Pivot.PivotBack;
import frc.robot.commands.Pivot.PivotFront;
import frc.robot.commands.Pivot.StopPivot;
import frc.robot.commands.Shoot.BallPushIn;
import frc.robot.commands.Shoot.BallPushOut;
import frc.robot.commands.Shoot.Shooter;
import frc.robot.commands.Shoot.StopShooter;
import frc.robot.commands.Drive.DefaultDrive;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;


/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  
  public static Chassis m_chassis = new Chassis();

  public static Arm m_Arm = new Arm();
  public static BallMover m_ballMover = new BallMover();
  public static BallPickup m_ballPickup = new BallPickup();
  public static Lift m_lift = new Lift();
  public static Shoot m_Shoot = new Shoot();  
  public static Pivot m_Pivot = new Pivot();
  public static WheelOfFortune m_WOF = new WheelOfFortune();

  public static XboxController m_driver = new XboxController(0);
  public static XboxController m_operator = new XboxController(1);

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    m_chassis.setDefaultCommand(
      new DefaultDrive(m_chassis, 
      () -> m_driver.getY(GenericHID.Hand.kLeft) * -1, 
      () -> m_driver.getX(GenericHID.Hand.kRight)));
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    
    
    /*
    //X Button
    new JoystickButton(m_driver, Button.kX.value) //lift pull up
    .whenPressed(new LiftUp(m_lift))
    .whenReleased(new StopLift(m_lift));
    
    //Y button
    new JoystickButton(m_driver, Button.kY.value) //lift release down
    .whenPressed(new LiftDown(m_lift))
    .whenReleased(new StopLift(m_lift));
    */

    new JoystickButton(m_operator, Button.kStart.value)
    .whenPressed(new DeployPickup(m_ballPickup));

    new JoystickButton(m_operator, Button.kBack.value)
    .whenPressed(new RetractPickup(m_ballPickup));

    new JoystickButton(m_operator, Button.kBumperLeft.value) //Move balls in
    .whenPressed(new BallForward(m_ballMover))
    .whenReleased(new BallStop(m_ballMover));
    
    new JoystickButton(m_operator, Button.kBumperRight.value) //ball intake
    .whenPressed(new BallUp(m_ballPickup))
    .whenReleased(new StopPickup(m_ballPickup));

    new JoystickButton(m_operator, Button.kB.value) //move balls back
    .whenPressed(new BallBackward(m_ballMover))
    .whenReleased(new BallStop(m_ballMover));

    new JoystickButton(m_operator, Button.kA.value) // shooter
    .whenPressed(new Shooter(m_Shoot))
    .whenReleased(new StopShooter(m_Shoot));

    new JoystickButton(m_operator, Button.kStickLeft.value)
    .whenPressed(new BallPushIn(m_Shoot))
    .whenReleased(new BallPushOut(m_Shoot));

    //Left Bumper
    new JoystickButton(m_operator, Button.kX.value) //pivot cannon front
    .whenPressed(new PivotFront(m_Pivot))
    .whenReleased(new StopPivot(m_Pivot));

    //Right bumper
    new JoystickButton(m_operator, Button.kY.value) //pivot cannon back
    .whenPressed(new PivotBack(m_Pivot))
    .whenReleased(new StopPivot(m_Pivot));
    
    /*
    //Start button
    new JoystickButton(m_operator, Button.kStart.value) //spin wof wheel
    .whenPressed(new ForwardSpin(m_WOF))
    .whenReleased(new StopSpin(m_WOF));

    //back button
    new JoystickButton(m_operator, Button.kBack.value) //reverse wof wheel
    .whenPressed(new BackwardSpin(m_WOF))
    .whenReleased(new StopSpin(m_WOF));
    */

    new JoystickButton(m_driver, Button.kA.value)
    .whenPressed(new LiftArmUp(m_Arm))
    .whenReleased(new ArmStop(m_Arm));

    new JoystickButton(m_driver, Button.kB.value)
    .whenPressed(new ArmDown(m_Arm))
    .whenReleased(new ArmStop(m_Arm));
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
