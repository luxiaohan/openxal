package se.lu.esss.ics.jels;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import se.lu.esss.ics.jels.smf.impl.ESSBend;
import xal.model.ModelException;
import xal.model.probe.Probe;
import xal.sim.scenario.ElementMapping;
import xal.smf.AcceleratorSeq;
import xal.smf.impl.qualify.MagnetType;
import xal.tools.beam.IConstants;

@RunWith(Parameterized.class)
public class BendTest extends TestCommon {
	public BendTest(Probe probe, ElementMapping elementMapping) {
		super(probe, elementMapping);
	}
	
	/*
	 * Test used for madx comparison.
	 * 
	 * */
	
// 	@Test
	public void doHorizontalBendTestMadX() throws InstantiationException, ModelException {
		probe.reset();
		System.out.println("Horizontal madx");
		
		AcceleratorSeq sequence = bend(-5.5, -11, -5.5, 9375.67, 0., 0, 0., 0, 0., 0, 0, 0);
		
		run(sequence);
		
		printResults();
		
//		checkTWTransferMatrix(new double[][]{});
	}
	
	
	@Test
	public void doVerticalBendTest() throws InstantiationException, ModelException {
		probe.reset();
		System.out.println("Vertical");
		/*
		EDGE -5.5 9375.67 50 0.45 2.8 50 1; this is a magnet length of 1.8 m 
		BEND -11 9375.67 0 50 1
		EDGE -5.5 9375.67 50 0.45 2.8 50 1
		 */		
		AcceleratorSeq sequence = bend(-5.5, -11, -5.5, 9375.67, 0., 50, 0.45, 2.80, 0.45, 2.80, 50, 1);
		
		run(sequence);

		//printResults();
		if (initialEnergy == 3e6) {
			checkELSResults(1.799999E+00, new double[] {6.182466E-03, 5.210289E-03, 5.142904E-03},
					new double [] { 1.458045E+01, 1.039017E+01, 7.424592E+00}); // when halfMag=true
			
			checkTWTransferMatrix(new double [][] {
					{+1.018958e+00, +1.799999e+00, +0.000000e+00, +0.000000e+00, +0.000000e+00, +0.000000e+00}, 
					{+2.126416e-02, +1.018958e+00, +0.000000e+00, +0.000000e+00, +0.000000e+00, +0.000000e+00}, 
					{+0.000000e+00, +0.000000e+00, +9.632544e-01, +1.788962e+00, +0.000000e+00, -1.722575e-01}, 
					{+0.000000e+00, +0.000000e+00, -4.032563e-02, +9.632544e-01, +0.000000e+00, -1.890399e-01}, 
					{+0.000000e+00, +0.000000e+00, +1.890399e-01, +1.722575e-01, +1.000000e+00, +1.777507e+00}, 
					{+0.000000e+00, +0.000000e+00, +0.000000e+00, +0.000000e+00, +0.000000e+00, +1.000000e+00}, 
			});
			
			checkTWResults(1.003197291, new double[][] {
					{+3.822288e-11, +2.081295e-11, +0.000000e+00, +0.000000e+00, +0.000000e+00, +0.000000e+00}, 
					{+2.081295e-11, +1.151277e-11, +0.000000e+00, +0.000000e+00, +0.000000e+00, +0.000000e+00}, 
					{+0.000000e+00, +0.000000e+00, +2.730380e-11, +1.338170e-11, +9.152883e-13, -9.096619e-13}, 
					{+0.000000e+00, +0.000000e+00, +1.338170e-11, +6.868014e-12, -7.836318e-13, -9.982870e-13}, 
					{+0.000000e+00, +0.000000e+00, +9.152883e-13, -7.836318e-13, +2.675748e-11, +1.126873e-11}, 
					{+0.000000e+00, +0.000000e+00, -9.096619e-13, -9.982870e-13, +1.126873e-11, +5.280827e-12} 
			});
		}
		if (initialEnergy == 2.5e9) {
			checkTWTransferMatrix(new double [][] {
					{+1.018958e+00, +1.799999e+00, +0.000000e+00, +0.000000e+00, +0.000000e+00, +0.000000e+00}, 
					{+2.126416e-02, +1.018958e+00, +0.000000e+00, +0.000000e+00, +0.000000e+00, +0.000000e+00}, 
					{+0.000000e+00, +0.000000e+00, +9.632544e-01, +1.788962e+00, +0.000000e+00, -1.722575e-01}, 
					{+0.000000e+00, +0.000000e+00, -4.032563e-02, +9.632544e-01, +0.000000e+00, -1.890399e-01}, 
					{+0.000000e+00, +0.000000e+00, +1.890399e-01, +1.722575e-01, +1.000000e+00, +1.230120e-01}, 
					{+0.000000e+00, +0.000000e+00, +0.000000e+00, +0.000000e+00, +0.000000e+00, +1.000000e+00}, 
			});
			
			checkTWResults(3.664409209, new double[][] {
					{+8.677160e-13, +4.724847e-13, +0.000000e+00, +0.000000e+00, +0.000000e+00, +0.000000e+00}, 
					{+4.724847e-13, +2.613568e-13, +0.000000e+00, +0.000000e+00, +0.000000e+00, +0.000000e+00}, 
					{+0.000000e+00, +0.000000e+00, +6.637417e-13, +3.519667e-13, +2.359157e-14, -2.755303e-13}, 
					{+0.000000e+00, +0.000000e+00, +3.519667e-13, +2.087907e-13, -1.470235e-14, -3.023743e-13}, 
					{+0.000000e+00, +0.000000e+00, +2.359157e-14, -1.470235e-14, +4.693770e-14, +2.394856e-13}, 
					{+0.000000e+00, +0.000000e+00, -2.755303e-13, -3.023743e-13, +2.394856e-13, +1.599526e-12}, 
			});
		}
	}
	
	
	@Test
	public void doHorizontalBendTest() throws InstantiationException, ModelException {
		probe.reset();
		System.out.println("Horizontal");
		/*
		EDGE -5.5 9375.67 50 0.45 2.8 50 0; this is a magnet length of 1.8 m 
		BEND -11 9375.67 0 50 0
		EDGE -5.5 9375.67 50 0.45 2.8 50 0
		 */		
		AcceleratorSeq sequence = bend(-5.5, -11, -5.5, 9375.67, 0., 50, 0.45, 2.80, 0.45, 2.80, 50, 0);
		
		run(sequence);

		//printResults();
		if (initialEnergy == 3e6) {
			checkELSResults(1.799999E+00, new double[] {6.132800E-03, 5.266670E-03, 5.142904E-03},
					new double [] { 1.434713E+01, 1.061625E+01, 7.424592E+00}); 
			
			checkTWTransferMatrix(new double [][] {
					{+9.632544e-01, +1.788962e+00, +0.000000e+00, +0.000000e+00, +0.000000e+00, -1.722575e-01}, 
					{-4.032563e-02, +9.632544e-01, +0.000000e+00, +0.000000e+00, +0.000000e+00, -1.890399e-01}, 
					{+0.000000e+00, +0.000000e+00, +1.018958e+00, +1.799999e+00, +0.000000e+00, +0.000000e+00}, 
					{+0.000000e+00, +0.000000e+00, +2.126416e-02, +1.018958e+00, +0.000000e+00, +0.000000e+00}, 
					{+1.890399e-01, +1.722575e-01, +0.000000e+00, +0.000000e+00, +1.000000e+00, +1.777507e+00}, 
					{+0.000000e+00, +0.000000e+00, +0.000000e+00, +0.000000e+00, +0.000000e+00, +1.000000e+00}, 
			});
			
			checkTWResults(1.003197291, new double[][] {
					{+3.776793e-11, +1.961660e-11, +0.000000e+00, +0.000000e+00, +1.819416e-12, -9.096619e-13}, 
					{+1.961660e-11, +1.042412e-11, +0.000000e+00, +0.000000e+00, -2.175526e-13, -9.982870e-13}, 
					{+0.000000e+00, +0.000000e+00, +2.773781e-11, +1.426590e-11, +0.000000e+00, +0.000000e+00}, 
					{+0.000000e+00, +0.000000e+00, +1.426590e-11, +7.583238e-12, +0.000000e+00, +0.000000e+00}, 
					{+1.819416e-12, -2.175526e-13, +0.000000e+00, +0.000000e+00, +2.683088e-11, +1.126873e-11}, 
					{-9.096619e-13, -9.982870e-13, +0.000000e+00, +0.000000e+00, +1.126873e-11, +5.280827e-12}, 
			});
		}
		if (initialEnergy == 2.5e9) {
			checkTWTransferMatrix(new double [][] {
					{+9.632544e-01, +1.788962e+00, +0.000000e+00, +0.000000e+00, +0.000000e+00, -1.722575e-01}, 
					{-4.032563e-02, +9.632544e-01, +0.000000e+00, +0.000000e+00, +0.000000e+00, -1.890399e-01}, 
					{+0.000000e+00, +0.000000e+00, +1.018958e+00, +1.799999e+00, +0.000000e+00, +0.000000e+00}, 
					{+0.000000e+00, +0.000000e+00, +2.126416e-02, +1.018958e+00, +0.000000e+00, +0.000000e+00}, 
					{+1.890399e-01, +1.722575e-01, +0.000000e+00, +0.000000e+00, +1.000000e+00, +1.230120e-01}, 
					{+0.000000e+00, +0.000000e+00, +0.000000e+00, +0.000000e+00, +0.000000e+00, +1.000000e+00}, 
			});
			
			checkTWResults(3.664409209, new double[][] {
					{+9.012927e-13, +4.935083e-13, +0.000000e+00, +0.000000e+00, +4.411661e-14, -2.755303e-13}, 
					{+4.935083e-13, +2.895197e-13, +0.000000e+00, +0.000000e+00, -1.851520e-15, -3.023743e-13}, 
					{+0.000000e+00, +0.000000e+00, +6.296894e-13, +3.238570e-13, +0.000000e+00, +0.000000e+00}, 
					{+0.000000e+00, +0.000000e+00, +3.238570e-13, +1.721507e-13, +0.000000e+00, +0.000000e+00}, 
					{+4.411661e-14, -1.851520e-15, +0.000000e+00, +0.000000e+00, +4.860410e-14, +2.394856e-13}, 
					{-2.755303e-13, -3.023743e-13, +0.000000e+00, +0.000000e+00, +2.394856e-13, +1.599526e-12}, 
			});
		}
	}
	
	
	@Test
	public void doHorizontalBendTest2() throws InstantiationException, ModelException {
		probe.reset();
		System.out.println("Horizontal N=0.2");
		/*
		EDGE -5.5 9375.67 50 0.45 2.8 50 0; this is a magnet length of 1.8 m 
		BEND -11 9375.67 0.2 50 0
		EDGE -5.5 9375.67 50 0.45 2.8 50 0
		 */		
		AcceleratorSeq sequence = bend(-5.5, -11, -5.5, 9375.67, 0.2, 50, 0.45, 2.80, 0.45, 2.80, 50, 0);
		
		run(sequence);

		//printResults();
		
		if (initialEnergy == 3e6) {
			checkELSResults(1.799999E+00, new double[] {6.132800E-03, 5.266670E-03, 5.142904E-03},
					new double [] { 1.434713E+01, 1.061625E+01, 7.424592E+00}); 
			
			checkTWTransferMatrix(new double [][] {
					{+9.668973e-01, +1.791166e+00, +0.000000e+00, +0.000000e+00, +0.000000e+00, -1.723634e-01}, 
					{-3.635045e-02, +9.668973e-01, +0.000000e+00, +0.000000e+00, +0.000000e+00, -1.892739e-01}, 
					{+0.000000e+00, +0.000000e+00, +1.015251e+00, +1.797789e+00, +0.000000e+00, +0.000000e+00}, 
					{+0.000000e+00, +0.000000e+00, +1.709594e-02, +1.015251e+00, +0.000000e+00, +0.000000e+00}, 
					{+1.892739e-01, +1.723634e-01, +0.000000e+00, +0.000000e+00, +1.000000e+00, +1.777503e+00}, 
					{+0.000000e+00, +0.000000e+00, +0.000000e+00, +0.000000e+00, +0.000000e+00, +1.000000e+00}, 
	 
			});
			
			checkTWResults(1.003197291, new double[][] {
					{+3.786796e-11, +1.972151e-11, +0.000000e+00, +0.000000e+00, +1.825838e-12, -9.102212e-13}, 
					{+1.972151e-11, +1.050564e-11, +0.000000e+00, +0.000000e+00, -2.108931e-13, -9.995228e-13}, 
					{+0.000000e+00, +0.000000e+00, +2.765705e-11, +1.418382e-11, +0.000000e+00, +0.000000e+00}, 
					{+0.000000e+00, +0.000000e+00, +1.418382e-11, +7.520955e-12, +0.000000e+00, +0.000000e+00}, 
					{+1.825838e-12, -2.108931e-13, +0.000000e+00, +0.000000e+00, +2.683131e-11, +1.126871e-11}, 
					{-9.102212e-13, -9.995228e-13, +0.000000e+00, +0.000000e+00, +1.126871e-11, +5.280827e-12}, 
	
			});
		}
		if (initialEnergy == 2.5e9) {
			checkTWTransferMatrix(new double [][] {
					{+9.668973e-01, +1.791166e+00, +0.000000e+00, +0.000000e+00, +0.000000e+00, -1.723634e-01}, 
					{-3.635045e-02, +9.668973e-01, +0.000000e+00, +0.000000e+00, +0.000000e+00, -1.892739e-01}, 
					{+0.000000e+00, +0.000000e+00, +1.015251e+00, +1.797789e+00, +0.000000e+00, +0.000000e+00}, 
					{+0.000000e+00, +0.000000e+00, +1.709594e-02, +1.015251e+00, +0.000000e+00, +0.000000e+00}, 
					{+1.892739e-01, +1.723634e-01, +0.000000e+00, +0.000000e+00, +1.000000e+00, +1.230079e-01}, 
					{+0.000000e+00, +0.000000e+00, +0.000000e+00, +0.000000e+00, +0.000000e+00, +1.000000e+00}, 
			});
			
			checkTWResults( 3.664409209, new double[][] {
					{+9.036177e-13, +4.959792e-13, +0.000000e+00, +0.000000e+00, +4.426517e-14, -2.756997e-13}, 
					{+4.959792e-13, +2.915013e-13, +0.000000e+00, +0.000000e+00, -1.695376e-15, -3.027486e-13}, 
					{+0.000000e+00, +0.000000e+00, +6.278559e-13, +3.219938e-13, +0.000000e+00, +0.000000e+00}, 
					{+0.000000e+00, +0.000000e+00, +3.219938e-13, +1.707368e-13, +0.000000e+00, +0.000000e+00}, 
					{+4.426517e-14, -1.695376e-15, +0.000000e+00, +0.000000e+00, +4.861388e-14, +2.394791e-13}, 
					{-2.756997e-13, -3.027486e-13, +0.000000e+00, +0.000000e+00, +2.394791e-13, +1.599526e-12}
			});	
		}
	}
	
	@Test
	public void doVerticalBendTest2() throws InstantiationException, ModelException {
		probe.reset();
		System.out.println("Vertical N=0.9");
		/*
		EDGE -5.5 9375.67 50 0.45 2.8 50 1; this is a magnet length of 1.8 m 
		BEND -11 9375.67 0.9 50 1
		EDGE -5.5 9375.67 50 0.45 2.8 50 1
		 */		
		AcceleratorSeq sequence = bend(-5.5, -11, -5.5, 9375.67, 0.9, 50, 0.45, 2.80, 0.45, 2.80, 50, 1);
		
		run(sequence);

		//printResults();
		if (initialEnergy == 3e6) {
			checkTWTransferMatrix(new double [][] {
					{+1.002313e+00, +1.790064e+00, +0.000000e+00, +0.000000e+00, +0.000000e+00, +0.000000e+00}, 
					{+2.587002e-03, +1.002313e+00, +0.000000e+00, +0.000000e+00, +0.000000e+00, +0.000000e+00}, 
					{+0.000000e+00, +0.000000e+00, +9.796828e-01, +1.798894e+00, +0.000000e+00, -1.727345e-01}, 
					{+0.000000e+00, +0.000000e+00, -2.235906e-02, +9.796828e-01, +0.000000e+00, -1.900943e-01}, 
					{+0.000000e+00, +0.000000e+00, +1.900943e-01, +1.727345e-01, +1.000000e+00, +1.777489e+00}, 
					{+0.000000e+00, +0.000000e+00, +0.000000e+00, +0.000000e+00, +0.000000e+00, +1.000000e+00}, 
			});
			
			checkTWResults(1.003197291, new double[][] {
					{+3.776964e-11, +2.032777e-11, +0.000000e+00, +0.000000e+00, +0.000000e+00, +0.000000e+00}, 
					{+2.032777e-11, +1.112244e-11, +0.000000e+00, +0.000000e+00, +0.000000e+00, +0.000000e+00}, 
					{+0.000000e+00, +0.000000e+00, +2.766317e-11, +1.373920e-11, +9.388964e-13, -9.121809e-13}, 
					{+0.000000e+00, +0.000000e+00, +1.373920e-11, +7.129682e-12, -7.619018e-13, -1.003855e-12}, 
					{+0.000000e+00, +0.000000e+00, +9.388964e-13, -7.619018e-13, +2.675914e-11, +1.126863e-11}, 
					{+0.000000e+00, +0.000000e+00, -9.121809e-13, -1.003855e-12, +1.126863e-11, +5.280827e-12}, 
	
			});
		}
		if (initialEnergy == 2.5e9) {
			checkTWTransferMatrix(new double [][] {
					{+1.002313e+00, +1.790064e+00, +0.000000e+00, +0.000000e+00, +0.000000e+00, +0.000000e+00}, 
					{+2.587002e-03, +1.002313e+00, +0.000000e+00, +0.000000e+00, +0.000000e+00, +0.000000e+00}, 
					{+0.000000e+00, +0.000000e+00, +9.796828e-01, +1.798894e+00, +0.000000e+00, -1.727345e-01}, 
					{+0.000000e+00, +0.000000e+00, -2.235906e-02, +9.796828e-01, +0.000000e+00, -1.900943e-01}, 
					{+0.000000e+00, +0.000000e+00, +1.900943e-01, +1.727345e-01, +1.000000e+00, +1.229937e-01}, 
					{+0.000000e+00, +0.000000e+00, +0.000000e+00, +0.000000e+00, +0.000000e+00, +1.000000e+00}, 
 
			});
			
			checkTWResults( 3.664409209, new double[][] {
					{+8.574267e-13, +4.614705e-13, +0.000000e+00, +0.000000e+00, +0.000000e+00, +0.000000e+00}, 
					{+4.614705e-13, +2.524959e-13, +0.000000e+00, +0.000000e+00, +0.000000e+00, +0.000000e+00}, 
					{+0.000000e+00, +0.000000e+00, +6.721433e-13, +3.604854e-13, +2.413998e-14, -2.762933e-13}, 
					{+0.000000e+00, +0.000000e+00, +3.604854e-13, +2.153225e-13, -1.418668e-14, -3.040608e-13}, 
					{+0.000000e+00, +0.000000e+00, +2.413998e-14, -1.418668e-14, +4.697599e-14, +2.394563e-13}, 
					{+0.000000e+00, +0.000000e+00, -2.762933e-13, -3.040608e-13, +2.394563e-13, +1.599526e-12}, 	
			});
		}
	}
	
	
	
	/**
	 * 
	 * @param entry_angle_deg
	 * @param alpha_deg angle in degrees
	 * @param exit_angle_deg
	 * @param rho absolute curvature radius
	 * @param N field Index
	 * @param G gap
	 * @param entrK1
	 * @param entrK2
	 * @param exitK1
	 * @param exitK2
	 * @param R aperture
	 * @param HV 0 - horizontal, 1 - vertical 
	 * @return sequence
	 */
	public AcceleratorSeq bend(double entry_angle_deg, double alpha_deg, double exit_angle_deg, double rho,
			double N, double G, double entrK1, double entrK2, double exitK1, double exitK2, double R, int HV)			
	{
		AcceleratorSeq sequence = new AcceleratorSeq("BendTest");
		
		// mm -> m
		rho *= 1e-3; 
		G *= 1e-3;
		R *= 1e-3;
		
		// calculations		
		double len = Math.abs(rho*alpha_deg * Math.PI/180.0);
		double quadComp = - N / (rho*rho);
		
		// following are used to calculate field		
	    double c  = IConstants.LightSpeed;	      
	    double e = probe.getSpeciesCharge();
	    double Er = probe.getSpeciesRestEnergy();
	    double gamma = probe.getGamma();
	    double b  = probe.getBeta();
	    
	    double k = b*gamma*Er/(e*c); // = -0.22862458629665997
	    double B0 = k/rho*Math.signum(alpha_deg);
	    //double B0 = b*gamma*Er/(e*c*rho)*Math.signum(alpha);
			    
		ESSBend bend = new ESSBend("b", HV == 0 ? MagnetType.HORIZONTAL : MagnetType.VERTICAL);
		bend.setPosition(len*0.5); //always position on center!
		bend.setLength(len); // both paths are used in calculation
		bend.getMagBucket().setPathLength(len);
		
		bend.getMagBucket().setDipoleEntrRotAngle(-entry_angle_deg);
		bend.getMagBucket().setBendAngle(alpha_deg);
		bend.getMagBucket().setDipoleExitRotAngle(-exit_angle_deg);		
		bend.setDfltField(B0);		
		bend.getMagBucket().setDipoleQuadComponent(quadComp);
		
		bend.setGap(G);
		bend.setEntrK1(entrK1);
		bend.setEntrK2(entrK2);
		bend.setExitK1(exitK1);
		bend.setExitK2(exitK2);
		
		sequence.addNode(bend);
		sequence.setLength(len);
		
		return sequence;
	}
	
}
