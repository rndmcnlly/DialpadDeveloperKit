package as.adamsmith.etherealdialpad.dsp;

interface ISynthService {
	
	// first finger on x-y pad
	void primaryOn();
	void primaryOff();
	void primaryXY(float x, float y);
	
	// second finger on x-y pad
	void secondaryOn();
	void secondaryOff();
	void secondaryXY(float x, float y);

	// synth config
	float[] getScale();
	boolean isDuet();
	int getOctaves();

}