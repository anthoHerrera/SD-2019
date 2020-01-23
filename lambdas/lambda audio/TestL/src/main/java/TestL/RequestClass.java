package TestL;

public class RequestClass {
	String keyName;
    String formato;
    int bitRate;
    int channel;
    int samplingRate;
    String fileObjKeyName;
    
	public String getFileObjKeyName() {
		return fileObjKeyName;
	}
	public void setFileObjKeyName(String fileObjKeyName) {
		this.fileObjKeyName = fileObjKeyName;
	}
	public String getKeyName() {
		return keyName;
	}
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
	public String getFormato() {
		return formato;
	}
	public void setFormato(String formato) {
		this.formato = formato;
	}
	public int getBitRate() {
		return bitRate;
	}
	public void setBitRate(int bitRate) {
		this.bitRate = bitRate;
	}
	public int getChannel() {
		return channel;
	}
	public void setChannel(int channel) {
		this.channel = channel;
	}
	public int getSamplingRate() {
		return samplingRate;
	}
	public void setSamplingRate(int samplingRate) {
		this.samplingRate = samplingRate;
	}
    
	public RequestClass(String keyName, String formato, int bitRate, int channel, int samplingRate,
			String fileObjKeyName) {
		super();
		this.keyName = keyName;
		this.formato = formato;
		this.bitRate = bitRate;
		this.channel = channel;
		this.samplingRate = samplingRate;
		this.fileObjKeyName = fileObjKeyName;
	}
	
	public RequestClass() {
    }
    

    
}
