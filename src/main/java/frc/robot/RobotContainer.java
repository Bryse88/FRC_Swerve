// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.io.IOException;
import java.nio.file.Path;
import java.util.function.DoubleSupplier;

import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Commands.ArmsBackward;
import frc.robot.Commands.ArmsForward;
import frc.robot.Commands.Intake;
import frc.robot.Commands.IntakeStop;
import frc.robot.Commands.OutakeStop;
import frc.robot.Commands.Outake;
import frc.robot.subsystems.DriveSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveSubsystem m_robotDrive = new DriveSubsystem();

  private final Joystick m_driverController = new Joystick(Constants.OIConstants.kDriverControllerPort);
  private final XboxController m_DriverController2 = new XboxController(Constants.OIConstants.kDriver2ControllerPort);
  private JoystickButton a, b, x, y, leftBumper, rightBumper, back, start, elevatorUpButton, elevatorDownButton;

   // A chooser for autonomous commands
   SendableChooser<Command> chooser = new SendableChooser<>();
   String trajectoryJSON = "paths/move-forward.wpilib.json";
   Trajectory trajectory = new Trajectory();



  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    
    try {
      Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON);
      trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
   } catch (IOException ex) {
      DriverStation.reportError("Unable to open trajectory: " + trajectoryJSON, ex.getStackTrace());
   }

    // Configure the button bindings
    configureButtonBindings();

    //m_elevator.register();//needed to use periodic with command scheduler

  }

  /**x`
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    
  }

  public void teleopInitFunc() {

    DoubleSupplier LEFT_STICK_X = () -> m_driverController.getRawAxis(0);
    DoubleSupplier LEFT_STICK_Y = () -> m_driverController.getRawAxis(1);
    DoubleSupplier RIGHT_STICK_X = () -> m_driverController.getRawAxis(2);
    DoubleSupplier RIGHT_STICK_Y = () -> m_driverController.getRawAxis(3);
    //JoystickButton A_BUTTON = new JoystickButton(m_driverController, 1);
    JoystickButton RIGHT_BUMPER = new JoystickButton(m_driverController, 6);
    JoystickButton triggerButton = new JoystickButton(m_driverController, 1);
    JoystickButton SideButton = new JoystickButton(m_driverController, 2);
    JoystickButton OtherButton = new JoystickButton(m_driverController, 12);


    a = new JoystickButton(m_DriverController2, 1);
    b = new JoystickButton(m_DriverController2, 2);
    x = new JoystickButton(m_DriverController2, 3);
    y = new JoystickButton(m_DriverController2, 4);


    /*
      * Sets the default command and joystick bindings for the drive train.
      * NOTE: The left stick controls translation of the robot. Turning is controlled by the X axis of the right stick.
      */
    m_robotDrive.setDefaultCommand(
        new RunCommand(
            () ->
                m_robotDrive.drive(
                    modifyAxisCubed(LEFT_STICK_Y) * -1 // xAxis
                        * Constants.DriveConstants.kMaxSpeedMetersPerSecond,
                    modifyAxisCubed(LEFT_STICK_X) * -1 // yAxis
                        * Constants.DriveConstants.kMaxSpeedMetersPerSecond,
                    modifyAxisCubed(RIGHT_STICK_X) * 1//-1 // rot CCW positive
                        * Constants.DriveConstants.kMaxRotationalSpeed,
                    !RIGHT_BUMPER.getAsBoolean()),
            m_robotDrive));
        
      //a.onTrue(new ArmsBackward());
      //b.onTrue(new ArmsForward());

      triggerButton.whileFalse(new ArmsForward());
      triggerButton.whileTrue(new ArmsBackward());
      SideButton.whileTrue(new Intake());
      OtherButton.whileTrue(new Outake());
      SideButton.whileFalse(new IntakeStop());
      OtherButton.whileFalse(new OutakeStop());
      //if(SideButton == 1){

      //}
      System.out.println("Intake button: " + m_driverController.getRawButton(2));
      System.out.println("Outtake button: " + m_driverController.getRawButton(12));
      //SideButton.toggleOnTrue(new Intake());
      /*if(m_driverController.getRawButton(2)){
          new Intake();
      }
      else if(m_driverController.getRawButton(12)){
        new Outake();
      }
      else{
        new IntakeStop();
      }*/




    
  }


  


  private static double modifyAxisCubed(DoubleSupplier supplierValue) {
    double value = supplierValue.getAsDouble();

    // Deadband
    value = deadband(value, Constants.OIConstants.kDriverControllerDeadband);

    // Cube the axis
    value = Math.copySign(value * value * value, value);

    return value;
  }

  private static double deadband(double value, double deadband) {
    if (Math.abs(value) > deadband) {
      if (value > 0) {
        return (value - deadband) / (1.0 - deadband);
      } else {
        return (value + deadband) / (1.0 - deadband);
      }
    } else {
      return 0.0;
    }
  }


private void configureAutonCommand(){
}



  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return new SequentialCommandGroup(
      //new InstantCommand(() -> m_robotDrive.resetOdometry(trajectory.getInitialPose())),
      //swerveControllerCommand,
      new InstantCommand(() -> m_robotDrive.drive(1.33)),
      new InstantCommand(() -> m_robotDrive.driveNeg(4)),//4 meters was original
      new InstantCommand(() -> m_robotDrive.stop())
    );
  }
}