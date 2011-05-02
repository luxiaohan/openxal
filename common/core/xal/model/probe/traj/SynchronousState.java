/*
 * Created on Jun 1, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package xal.model.probe.traj;

import xal.tools.data.IDataAdaptor;
import xal.tools.math.r3.R3;
import xal.model.probe.SynchronousProbe;
import xal.model.xml.ParsingException;

/**
 * @author Christopher K. Allen
 */
public class SynchronousState extends ProbeState {
    

    /*
     * Global Constants
     */

    /** element tag for RF phase */
    protected static final String LABEL_SYNCH = "synch";
    
    /** attribute tag for betatron phase */
    protected static final String ATTR_PHASEBETA = "phasebeta";
    
    /** attribute tag for RF phase */
    protected static final String ATTR_PHASERF = "phaserf";



    /*
     * Local Attributes
     */
     
    /** Synchronous particle position with respect to any RF drive phase */
    private double              m_dblPhsRf;

    /** synchronous particle betatron phase without space charge */
    private R3                  m_vecPhsBeta;
    
    


    /*
     * Initialization
     */
     
     
    /**
     * Default constructor.  Create a new <code>SynchronousState</code> 
     * object with zero state values.
     */
    public SynchronousState() {
        super();
        this.m_dblPhsRf = 0.0;
        this.m_vecPhsBeta = R3.zero();
    }

    /**
     * Copy constructor.  Create a new <code>SynchronousState</code> object
     * and initialize the state to that of the specified probe argument.
     *  
     * @param probe     probe containing initializing state information
     */
    public SynchronousState(SynchronousProbe probe) {
        super(probe);
        this.setBetatronPhase( probe.getBetatronPhase() );
        this.setRfPhase( probe.getRfPhase() );
    }
    
    /**
     * Set the betatron phase of the synchronous particle without space charge.
     * The betatron phase of all three planes is maintained as an 
     * <code>R3</code> vector object.  Thus, the betatron phase of each plane
     * is set simultaneously.
     * 
     * @param vecPhase      vector (psix,psiy,psiz) of betatron phases in <b>radians</b>
     */
    public void     setBetatronPhase(R3 vecPhase)   {
        this.m_vecPhsBeta = vecPhase;
    }
    
    /**
     * Set the phase location of the synchronous particle with respect to the 
     * drive RF power.
     *  
     * @param dblPhase      synchronous particle phase w.r.t. RF in <b>radians</b>
     */
    public void     setRfPhase(double dblPhase) {
        this.m_dblPhsRf = dblPhase;
    }
    

    
    /*
     * Attribute Query
     */
    
    /**
     * Return the betatron phase advances in each plane.
     * 
     * @return  vector (psix,psiy,psiz) of betatron phases in <b>radians</b>
     */
    public R3   getBetatronPhase()  {
        return this.m_vecPhsBeta;
    }
    
    /**
     * Return the phase location of the synchronous particle with respect
     * to any driving RF power.
     * 
     * @return      phase location of synchronous particle in <b>radians</b>
     */
    public double   getRfPhase()    {
        return this.m_dblPhsRf;    
    }
    


    /*
     * ProbeState Protocol
     */

    /**
     * Save the probe state values to a data store represented by the 
     * <code>IDataAdaptor</code> interface.
     * 
     * @param daptState     data sink to receive state information
     * 
     * @see gov.sns.xal.model.probe.traj.ProbeState#addPropertiesTo(gov.sns.tools.data.IDataAdaptor)
     */
    @Override
    protected void addPropertiesTo(IDataAdaptor daptSink) {
        super.addPropertiesTo(daptSink);

        IDataAdaptor daptSync = daptSink.createChild(SynchronousState.LABEL_SYNCH);
        daptSync.setValue(SynchronousState.ATTR_PHASEBETA, this.getBetatronPhase().toString());
        daptSync.setValue(SynchronousState.ATTR_PHASERF, this.getRfPhase());
    }

    /**
     * Restore the state values for this probe state object from the data store
     * represented by the <code>IDataAdaptor</code> interface.
     * 
     * @param   daptSrc             data source for probe state information
     * @throws  ParsingException    error in data format
     * 
     * @see gov.sns.xal.model.probe.traj.ProbeState#readPropertiesFrom(gov.sns.tools.data.IDataAdaptor)
     */
    @Override
    protected void readPropertiesFrom(IDataAdaptor daptSrc)
        throws ParsingException 
    {
        super.readPropertiesFrom(daptSrc);

        IDataAdaptor daptSync = daptSrc.childAdaptor(SynchronousState.LABEL_SYNCH);
        if (daptSync == null)
            throw new ParsingException("SynchronousState#readPropertiesFrom(): no child element = " + LABEL_SYNCH);
        
        if (daptSync.hasAttribute(SynchronousState.ATTR_PHASEBETA));   {
            String  strBeta = daptSync.stringValue(SynchronousState.ATTR_PHASEBETA);
            this.setBetatronPhase( new R3( strBeta ) );
        }
        if (daptSync.hasAttribute(SynchronousState.ATTR_PHASERF))
            this.setRfPhase( daptSync.doubleValue(SynchronousState.ATTR_PHASERF) );
    }

    /**
     * Returns a string representation of this particle state.  Currently returns only 
     * the super class implementation.
     * 
     * @return      the value <code>super.toString()</code>
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }

}