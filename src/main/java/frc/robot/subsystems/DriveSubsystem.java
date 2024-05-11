// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class DriveSubsystem extends SubsystemBase {

    private final ShuffleboardTab moduleTab = Shuffleboard.getTab("Module Info");
    private final SwerveModule m_frontLeft =
            new SwerveModule(
                    DriveConstants.kFrontLeftDriveMotorPort,
                    DriveConstants.kFrontLeftTurningMotorPort,
                    DriveConstants.kFrontLeftTurningEncoderPort,
                    DriveConstants.kFrontLeftAngleZero,
                    DriveConstants.kFrontLeftTurningEncoderReversed,
                    DriveConstants.kFrontLeftDriveEncoderReversed,
                    moduleTab.getLayout("Front Left Module", BuiltInLayouts.kList)
                            .withSize(4, 8)
                            .withPosition(0, 0));
    private final SwerveModule m_rearLeft =
            new SwerveModule(
                    DriveConstants.kRearLeftDriveMotorPort,
                    DriveConstants.kRearLeftTurningMotorPort,
                    DriveConstants.kRearLeftTurningEncoderPort,
                    DriveConstants.kRearLeftAngleZero,
                    DriveConstants.kRearLeftTurningEncoderReversed,
                    DriveConstants.kRearLeftDriveEncoderReversed,
                    moduleTab.getLayout("Rear Left Module", BuiltInLayouts.kList)
                            .withSize(4, 8)
                            .withPosition(4, 0));
    private final SwerveModule m_frontRight =
            new SwerveModule(
                    DriveConstants.kFrontRightDriveMotorPort,
                    DriveConstants.kFrontRightTurningMotorPort,
                    DriveConstants.kFrontRightTurningEncoderPort,
                    DriveConstants.kFrontRightAngleZero,
                    DriveConstants.kFrontRightTurningEncoderReversed,
                    DriveConstants.kFrontRightDriveEncoderReversed,
                    moduleTab.getLayout("Front Right Module", BuiltInLayouts.kList)
                            .withSize(4, 8)
                            .withPosition(8, 0));
    private final SwerveModule m_rearRight =
            new SwerveModule(
                    DriveConstants.kRearRightDriveMotorPort,
                    DriveConstants.kRearRightTurningMotorPort,
                    DriveConstants.kRearRightTurningEncoderPort,
                    DriveConstants.kRearRightAngleZero,
                    DriveConstants.kRearRightTurningEncoderReversed,
                    DriveConstants.kRearRightDriveEncoderReversed,
                    moduleTab.getLayout("Rear Right Module", BuiltInLayouts.kList)
                            .withSize(4, 8)
                            .withPosition(12, 0));

        //private final Gyro gyro = new AnalogGyro(0); 

     //   private final SwerveDriveOdometry odometer = new SwerveDriveOdometry(Constants.kDriveKinematics, new Rotation2d(0)); 

     public DriveSubsystem(){
        
        
     }
    // public void zeroHeading(){
    //     gyro.reset();
    // }
    // public double getHeading(){
    //     return Math.IEEEremainder(gyro.getAngle(), 360); 
    // }
    // public Rotation2d getRotation2d() {
    //     return Rotation2d.fromDegrees(getHeading());
    // }
    // public Pose2d getPose() {
    //     return odometer.getPoseMeters();
    // }
    // public void resetOdometry(Pose2d pose) {
    //     odometer.resetPosition(pose, getRotation2d());
    // }
    
    public double speed() {
        return 0;
    }


    @Override
    public void periodic() {
        // Update the odometry in the periodic block
        /*m_odometry.update(
                m_gyro.getRotation2d(),
                m_frontLeft.getState(),
                m_rearLeft.getState(),
                m_frontRight.getState(),
                m_rearRight.getState());*/


        //SmartDashboard.putNumber("Gyro Reading", getGyroAngle());

        m_frontLeft.periodic_func();
        m_rearRight.periodic_func();
        m_rearLeft.periodic_func();
        m_frontRight.periodic_func();
    }

    /**
     * Method to drive the robot using joystick info.
     *
     * @param xSpeed        Speed of the robot in the x direction (forward).
     * @param ySpeed        Speed of the robot in the y direction (sideways).
     * @param rot           Angular rate of the robot.
     * @param fieldRelative Whether the provided x and y speeds are relative to the field.
     */
    @SuppressWarnings("ParameterName")
    public void drive(double xSpeed, double ySpeed, double rot, boolean fieldRelative) {
        SmartDashboard.putBoolean("Field Relative:", fieldRelative);
        SwerveModuleState[] swerveModuleStates =
                DriveConstants.kDriveKinematics.toSwerveModuleStates(
                        new ChassisSpeeds(xSpeed, ySpeed, rot));
        setModuleStates(swerveModuleStates);
        
        //SwerveDriveKinematics.desaturateWheelSpeeds(
        //        swerveModuleStates, DriveConstants.kMaxSpeedMetersPerSecond);
        //m_frontLeft.setDesiredState(swerveModuleStates[0]);
        //m_frontRight.setDesiredState(swerveModuleStates[1]);
        //m_rearLeft.setDesiredState(swerveModuleStates[2]);
        //m_rearRight.setDesiredState(swerveModuleStates[3]);
//        SmartDashboard.putString("Front Left desired state: ", swerveModuleStates[0].toString());
//        SmartDashboard.putString("Front Right desired state: ", swerveModuleStates[1].toString());

    }


    /**
     * Sets the swerve ModuleStates.
     *
     * @param desiredStates The desired SwerveModule states.
     */
    public void setModuleStates(SwerveModuleState[] desiredStates) {
        SwerveDriveKinematics.desaturateWheelSpeeds(
                desiredStates, DriveConstants.kMaxSpeedMetersPerSecond);//reduces speed  to not exceed max.
        m_frontLeft.setDesiredState(desiredStates[0]);
        m_frontRight.setDesiredState(desiredStates[1]);
        m_rearLeft.setDesiredState(desiredStates[2]);
        m_rearRight.setDesiredState(desiredStates[3]);
    }

    public void drive(double meters) {
        long time = System.currentTimeMillis();
        while((System.currentTimeMillis()-time)<(1000*meters)) {
           drive(1,0, 0, false);
        }
        drive(0,0,0,false);
    }
    public void driveNeg(double meters) {
        long time = System.currentTimeMillis();
        while((System.currentTimeMillis()-time)<(1000*meters)) {
           drive(-1,0, 0, false);
        }
        drive(0,0,0,false);
    }

    public void stop() {
        drive(0,0,0,false);
     }


//  /**
//   * Returns the turn rate of the robot.
//   *
//   * @return The turn rate of the robot, in degrees per second
//   */
//  public double getTurnRate() {
////    return m_gyro.getRate() * (DriveConstants.kGyroReversed ? -1.0 : 1.0);
//    return m_gyro.getRate();
//  }
  
}
