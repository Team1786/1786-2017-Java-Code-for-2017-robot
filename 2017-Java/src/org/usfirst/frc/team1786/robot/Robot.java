package org.usfirst.frc.team1786.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	final String defaultAuto = "Default";
	final String customAuto = "My Auto";
	String autoSelected;
	SendableChooser<String> chooser = new SendableChooser<>();
	
	Joystick joystickLeft = new Joystick(0);
	Joystick joystickRight = new Joystick(1);
	
	CANTalon climber = new CANTalon(1);
	CANTalon ballPickup= new CANTalon(2);
	CANTalon shooter = new CANTalon(5);
	
	CANTalon talonRearRight = new CANTalon(3);
	CANTalon talonRearLeft = new CANTalon(4);
	CANTalon talonFrontRight= new CANTalon(6);
	CANTalon talonFrontRight = new CANTalon(7);
	
	double deadzone=.15;
	double deadzoneZ=.3;
	RobotDrive myRobot = new RobotDrive(leftFrontmotor, leftRearmotor, rightFrontmotor, rightRearmotor);
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		chooser.addDefault("Default Auto", defaultAuto);
		chooser.addObject("My Auto", customAuto);
		SmartDashboard.putData("Auto choices", chooser);
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
		autoSelected = chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector",
		// defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		switch (autoSelected) {
		case customAuto:
			// Put custom auto code here
			break;
		case defaultAuto:
		default:
			// Put default auto code here
			break;
		}
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		SmartDashboard.putNumber("joystickY", joystickLeft.getY());
		SmartDashboard.putNumber("joystickZ", joystickLeft.getZ());
		SmartDashboard.putNumber("joystickX", joystickLeft.getX());
		SmartDashboard.putNumber("modY", modXY(joystickLeft.GetY()));
		SmartDashboard.putNumber("modZ", modZ());
		SmartDashboard.putNumber("modX", modXY(joystickLeft.GetX()));
		//myRobot.mecanumDrive_Polar(modY(),modX(), modZ());
		//myRobot.mecanumDrive_Polar(modZ(), joystickLeft.getX(), modY());
		myRobot.mecanumDrive_Cartesian(modXY(joystickLeft.GetX()), modXY(joystickLeft.GetY()), modZ(), 0);
		
		
	}
	

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
	
	public double modXY(i) {
		if(-deadzone < i && i < deadzone)
			{ 
				return 0;
			}
		else 
			{
			return ((Math.abs(i)/i)*((Math.abs(i)-deadzone)/(1-deadzone));
			}
		}
	public double modZ() {
		if(-deadzoneZ < joystickLeft.getZ() && joystickLeft.getZ() < deadzoneZ)
			{ 
				return 0;
			}
		else 
			{
			return (joystickLeft.getZ()-deadzoneZ)/(1-deadzoneZ);
			}
		}


	}

