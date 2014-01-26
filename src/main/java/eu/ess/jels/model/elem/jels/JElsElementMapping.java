/*
 * DefaultElementMapping.java
 * 
 * Created on Oct 3, 2013
 */

package eu.ess.jels.model.elem.jels;

import xal.model.IComponent;
import xal.model.elem.IdealDrift;
import xal.model.elem.IdealMagQuad;
import xal.model.elem.Marker;
import xal.sim.scenario.ElementMapping;

/**
 * The default element mapping implemented as singleton.
 * 
 * @author Ivo List
 *
 */
public class JElsElementMapping extends ElementMapping {
	protected static ElementMapping instance;	

	public JElsElementMapping() {
		initialize();
	}
	
	/**
	 *  Returns the default element mapping.
	 *  
	 * @return the default element mapping
	 */
	public static ElementMapping getInstance()
	{
		if (instance == null) instance = new JElsElementMapping();
		return instance;
	}
	
	
	@Override
	public Class<? extends IComponent> getDefaultConverter() {
		return Marker.class;
	}

	@Override
	public IComponent createDrift(String name, double len) {
		return new IdealDrift(name, len);
	}
	
	protected void initialize() {
		putMap("dh", eu.ess.jels.model.elem.els.IdealMagWedgeDipole2.class);
		putMap("q", IdealMagQuad.class);
		putMap("qt", IdealMagQuad.class);
		putMap("pq", IdealMagQuad.class);
		putMap("rfgap", IdealRfGap.class);
		putMap("marker", Marker.class);
	}
}
