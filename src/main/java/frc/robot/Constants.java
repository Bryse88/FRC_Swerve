
package frc.robot;


import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.util.Units;


/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    public static final double wheelDiameterMeters = Units.inchesToMeters(3.1); 
    public static final double driveMotorGearRatio = 1 / 5.25; 
    public static final double angleMotorGearRatio = 1 / 133.25; 
    public static final double driveRot2Meter = driveMotorGearRatio * Math.PI * wheelDiameterMeters; 
    public static final double angleRot2Rad =   Math.PI * 2 * angleMotorGearRatio; 
    public static final double driveRPM2MPS = driveRot2Meter / 60; 
    public static final double angleRPM2RPS = angleRot2Rad / 60; 
    public static final double kPangle = 0.6; 
    public static final double kIangle = 0; 
    public static final double kDangle = 0; 
    //the max drive speed is small just for testing purposes. 
    public static final double MAXDriveSpeed = 3; 
    public static final double autoDriveSpeed = 0.2; 
    public static final double Deadband = 0.2; 

    public static final double MAX_VOLTAGE = 13.0;


    //left and right distance  
    public static final double trackWidth = Units.inchesToMeters(26); 
    //front and back distance
    public static final double wheelBase = Units.inchesToMeters(26);
    //
    public static final SwerveDriveKinematics kDriveKinematics = new SwerveDriveKinematics(
        new Translation2d(wheelBase / 2, -trackWidth /2),
        new Translation2d(wheelBase / 2, trackWidth /2), 
        new Translation2d(-wheelBase /2, -trackWidth /2),
        new Translation2d(-wheelBase /2, trackWidth /2)); 

    //module constants 
    public static final double kPhysicalMaxAngularSpeedRadiansPerSecond = 2 * 2 * Math.PI;
    public static final double kPhysicalMaxSpeedMetersPerSecond = 5;
    public static final double kTeleDriveMaxAccelerationUnitsPerSecond = 3;
    public static final double kTeleDriveMaxAngularAccelerationUnitsPerSecond = 3;
    public static final double kTeleDriveMaxSpeedMetersPerSecond = kPhysicalMaxSpeedMetersPerSecond / 8;
    public static final double kTeleDriveMaxAngularSpeedRadiansPerSecond = //
                kPhysicalMaxAngularSpeedRadiansPerSecond / 8;


    public static final double kTurningMotorGearRatio = 1 / 18.0;
    public static final double kWheelDiameterMeters = Units.inchesToMeters(4);
    public static final double kTurningEncoderRot2Rad = kTurningMotorGearRatio * 2 * Math.PI;
    public static final double kTurningEncoderRPM2RadPerSec = kTurningEncoderRot2Rad / 60;

    public static final double kPTurning = 0.5;


    
    //Drive Motor ID
    /*public static final int frontRight = 8;
    public static final int frontLeft = 5;
    public static final int backRight = 7;
    public static final int backLeft = 6;*/


    //turning motor ID
    /*public static final int a_frontRight = 4;
    public static final int a_frontLeft = 3;
    public static final int a_backRight = 2;
    public static final int a_backLeft = 1;*/


    //absolute encoder ID
    /*public static final int absolute_frontRight = 7;
    public static final int absolute_frontLeft = 0;
    public static final int absolute_backRight = 4;
    public static final int absolute_backLeft = 5;
    public static final boolean frontRight_absolute_Reversed = false;
    public static final boolean frontLeft_absolute_Reversed = false;
    public static final boolean backRight_absolute_Reversed = false;
    public static final boolean backLeft_absolute_Reversed = false;
    public static final double frontRight_absolute_OffsetRad = -0.254;
    public static final double frontLeft_absolute_OffsetRad = -1.252;
    public static final double backRight_absolute_OffsetRad = -1.816;
    public static final double backLeft_absolute_OffsetRad = -4.811;
    public static final boolean driveMotorReverse = true; 
    public static final boolean driveMotorForward = false;
    
    public static final boolean angleMotorReverse = true; 
    public static final boolean angleMotorFoward = false; 
    public static final double encoderOffsetRad = 4.376396; */

    public static final class DriveConstants {

        public static final int kFrontLeftDriveMotorPort = 7;
        public static final int kRearLeftDriveMotorPort = 6;
        public static final int kFrontRightDriveMotorPort = 8;
        public static final int kRearRightDriveMotorPort = 5;
    
        public static final int kFrontLeftTurningMotorPort = 4;
        public static final int kRearLeftTurningMotorPort = 2;
        public static final int kFrontRightTurningMotorPort = 3;
        public static final int kRearRightTurningMotorPort = 1;
    
        public static final int kFrontLeftTurningEncoderPort = 20;
        public static final int kRearLeftTurningEncoderPort = 22;
        public static final int kFrontRightTurningEncoderPort = 23;
        public static final int kRearRightTurningEncoderPort = 21;
    
        public static final double kFrontLeftAngleZero = -69;//-67;//-60;//-70;//-23;//-36;//79.45;
        public static final double kRearLeftAngleZero = 97.5;//97;//98;//105;//95;//123.5;//121.38;
        public static final double kFrontRightAngleZero = -157.5;//-158;//-157;//-150;//-160;//-185;//-113//-149; //-146;//53;//-104.68;
        public static final double kRearRightAngleZero = -112.5;//-113;//-112;//-105;//-115;//23.54;
    
        public static final boolean kFrontLeftTurningEncoderReversed = false;
        public static final boolean kRearLeftTurningEncoderReversed = false;
        public static final boolean kFrontRightTurningEncoderReversed = false;
        public static final boolean kRearRightTurningEncoderReversed = false;
    
        public static final boolean kFrontLeftDriveEncoderReversed = false;//true;
        public static final boolean kRearLeftDriveEncoderReversed = true;//false;
        public static final boolean kFrontRightDriveEncoderReversed = false;//true;
        public static final boolean kRearRightDriveEncoderReversed = false;//true;
    
        public static final double kTrackWidth = 0.57785;
        // Distance between centers of right and left wheels on robot
        public static final double kWheelBase = 0.57785;
        // Distance between front and back wheels on robot
        public static final SwerveDriveKinematics kDriveKinematics =
            new SwerveDriveKinematics(
                new Translation2d(kWheelBase / 2, kTrackWidth / 2),
                new Translation2d(kWheelBase / 2, -kTrackWidth / 2),
                new Translation2d(-kWheelBase / 2, kTrackWidth / 2),
                new Translation2d(-kWheelBase / 2, -kTrackWidth / 2));
    
        public static final boolean kGyroReversed = false;
    
    
        // Values to scale joystick inputs to desired states.
        public static final double kMaxSpeedMetersPerSecond = 4.5; // LOCKED IN AT 4.5
        public static final double kMaxRotationalSpeed =
            3 * Math.PI; //3*pi
    
        // These are example values only - DO NOT USE THESE FOR YOUR OWN ROBOT!
        // These characterization values MUST be determined either experimentally or theoretically
        // for *your* robot's drive.
        // The SysId tool provides a convenient method for obtaining these values for your robot.
        public static final double ksVolts = 0.73394;
        public static final double kvVoltSecondsPerMeter = 2.4068;
        public static final double kaVoltSecondsSquaredPerMeter = 0.28749;
    
        public static final double ksTurning = 0.77; // LOCKED IN!  -----  old 0.66202
        public static final double kvTurning = 0.75; //0.75 // 3.0052
        public static final double kaTurning = 0; // Default to zero
        public static final double kMaxAngularSpeedRadiansPerSecond = Math.PI * 2;
      }
      public static final class ModuleConstants {

        public static final double kDriveGearRatio = 7.36;//7.13;
    
        public static final double kPModuleTurnController = 5;//8.1; //8.3 // TUNE: 8.2142  //error
        public static final double kIModuleTurnController = 0; // DO NOT USE //Friction Error.  //error sum
        public static final double kDModuleTurnController = 0; // TUNE //This is the slope //error rate
    
        // Acceleration could be 8pi to make module get anywhere in 0.5 seconds.
        // Will never reach max velocity, so it can be right at the "top" of the triangle.
        // In this case, that would be 2pi.
    
        public static final double kMaxModuleAngularSpeedRadiansPerSecond = 3 * Math.PI;
        public static final double kMaxModuleAngularAccelerationRadiansPerSecondSquared = 6 * Math.PI;
    
        public static final double kPModuleDriveController = 1; // TUNE
        public static final double kIModuleDriveController = 0; // DO NOT USE
        public static final double kDModuleDriveController = 0;
    
    
        public static final int kDriveFXEncoderCPR = 2048;
        public static final int kTurningCANcoderCPR = 4096;
        public static final double kWheelDiameterMeters = 0.1016; // 4 inches
        public static final double kWheelCircumferenceMeters =
            kWheelDiameterMeters * Math.PI; // C = D * pi



            // This is the speed controller. We can up it and down. 

        public static final double kDrivetoMetersPerSecond = 0.01;
            //(10 * kWheelCircumferenceMeters) / (kDriveGearRatio * 2048);
            //(0.319*10) = 3.1918. / 15000 = 0.00021175604
            //1.25
      }

      public static final class Solenoid{

        public static final int Deploy1 = 0;
        public static final int Deploy2 = 2;
        public static final int Deploy3 = 4;

        public static final int Retract1 = 1;
        public static final int Retract2 = 3;
        public static final int Retract3 = 5;


      }


      public static final class OIConstants {

        public static final int kDriverControllerPort = 0;
        public static final int kDriver2ControllerPort = 1;
        public static final double kDriverControllerDeadband = 0.1;
        public static final int kDriverControllerZeroEncodersButton = 8;
        public static final int kDriverControllerZeroHeadingButton = 9;
      }

}