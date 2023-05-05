package com.melissadata;

public class mdEmailJNI {
	static {
		try {
			System.loadLibrary("mdEmailJavaWrapper");
		} catch (UnsatisfiedLinkError ule) {
			System.out.println(ule);
			System.out.println("java.library.path="+System.getProperty("java.library.path"));
		}
	}

	public final static native long mdEmailCreate();
	public final static native void mdEmailDestroy(long I);
	public final static native boolean SetLicenseString(long I,String License);
	public final static native void SetPathToEmailFiles(long I,String emailDataFiles);
	public final static native int InitializeDataFiles(long I);
	public final static native String GetInitializeErrorString(long I);
	public final static native String GetBuildNumber(long I);
	public final static native String GetDatabaseDate(long I);
	public final static native String GetDatabaseExpirationDate(long I);
	public final static native String GetLicenseStringExpirationDate(long I);
	public final static native boolean VerifyEmail(long I,String email);
	public final static native void SetCorrectSyntax(long I,boolean p1);
	public final static native void SetUpdateDomain(long I,boolean p1);
	public final static native void SetDatabaseLookup(long I,boolean p1);
	public final static native void SetFuzzyLookup(long I,boolean p1);
	public final static native void SetWSLookup(long I,boolean p1);
	public final static native void SetWSMailboxLookup(long I,int mailboxLookupmode);
	public final static native void SetMXLookup(long I,boolean p1);
	public final static native void SetStandardizeCasing(long I,boolean p1);
	public final static native String GetStatusCode(long I);
	public final static native String GetErrorCode(long I);
	public final static native String GetErrorString(long I);
	public final static native long GetChangeCode(long I);
	public final static native String GetResults(long I);
	public final static native String GetResultCodeDescription(long I,String resultCode,int opt);
	public final static native String GetMailBoxName(long I);
	public final static native String GetDomainName(long I);
	public final static native String GetTopLevelDomain(long I);
	public final static native String GetTopLevelDomainDescription(long I);
	public final static native String GetEmailAddress(long I);
	public final static native void SetReserved(long I,String Property,String Value_);
	public final static native String GetReserved(long I,String Property_);
	public final static native void SetCachePath(long I,String cachePath);
	public final static native void SetCacheUse(long I,int cacheUse);
}
