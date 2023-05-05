package com.melissadata;

public class mdEmail {
	private long I;
	protected boolean ownMemory;

	protected static long getI(mdEmail obj) {
		return (obj==null ? 0 : obj.I);
	}

	protected void finalize() {
		delete();
	}

public final static class ProgramStatus {
	public final static mdEmail.ProgramStatus ErrorNone=new mdEmail.ProgramStatus("ErrorNone",0);
	public final static mdEmail.ProgramStatus ErrorOther=new mdEmail.ProgramStatus("ErrorOther",1);
	public final static mdEmail.ProgramStatus ErrorOutOfMemory=new mdEmail.ProgramStatus("ErrorOutOfMemory",2);
	public final static mdEmail.ProgramStatus ErrorRequiredFileNotFound=new mdEmail.ProgramStatus("ErrorRequiredFileNotFound",3);
	public final static mdEmail.ProgramStatus ErrorFoundOldFile=new mdEmail.ProgramStatus("ErrorFoundOldFile",4);
	public final static mdEmail.ProgramStatus ErrorDatabaseExpired=new mdEmail.ProgramStatus("ErrorDatabaseExpired",5);
	public final static mdEmail.ProgramStatus ErrorLicenseExpired=new mdEmail.ProgramStatus("ErrorLicenseExpired",6);

	private final String enumName;
	private final int enumValue;
	private static ProgramStatus[] enumValues={ErrorNone,ErrorOther,ErrorOutOfMemory,ErrorRequiredFileNotFound,ErrorFoundOldFile,ErrorDatabaseExpired,ErrorLicenseExpired};

	private ProgramStatus(String name,int val) {
		enumName=name;
		enumValue=val;
	}

	public static ProgramStatus toEnum(int val) {
		for (int i=0;i<enumValues.length;i++)
			if (enumValues[i].enumValue==val)
				return enumValues[i];
		throw new IllegalArgumentException("No enum "+ProgramStatus.class+" with value "+val+".");
	}

	public String toString() {
		return enumName;
	}

	public int toValue() {
		return enumValue;
	}
}

public final static class AccessType {
	public final static mdEmail.AccessType Local=new mdEmail.AccessType("Local",0);
	public final static mdEmail.AccessType Remote=new mdEmail.AccessType("Remote",1);

	private final String enumName;
	private final int enumValue;
	private static AccessType[] enumValues={Local,Remote};

	private AccessType(String name,int val) {
		enumName=name;
		enumValue=val;
	}

	public static AccessType toEnum(int val) {
		for (int i=0;i<enumValues.length;i++)
			if (enumValues[i].enumValue==val)
				return enumValues[i];
		throw new IllegalArgumentException("No enum "+AccessType.class+" with value "+val+".");
	}

	public String toString() {
		return enumName;
	}

	public int toValue() {
		return enumValue;
	}
}

public final static class DiacriticsMode {
	public final static mdEmail.DiacriticsMode Auto=new mdEmail.DiacriticsMode("Auto",0);
	public final static mdEmail.DiacriticsMode On=new mdEmail.DiacriticsMode("On",1);
	public final static mdEmail.DiacriticsMode Off=new mdEmail.DiacriticsMode("Off",2);

	private final String enumName;
	private final int enumValue;
	private static DiacriticsMode[] enumValues={Auto,On,Off};

	private DiacriticsMode(String name,int val) {
		enumName=name;
		enumValue=val;
	}

	public static DiacriticsMode toEnum(int val) {
		for (int i=0;i<enumValues.length;i++)
			if (enumValues[i].enumValue==val)
				return enumValues[i];
		throw new IllegalArgumentException("No enum "+DiacriticsMode.class+" with value "+val+".");
	}

	public String toString() {
		return enumName;
	}

	public int toValue() {
		return enumValue;
	}
}

public final static class StandardizeMode {
	public final static mdEmail.StandardizeMode ShortFormat=new mdEmail.StandardizeMode("ShortFormat",0);
	public final static mdEmail.StandardizeMode LongFormat=new mdEmail.StandardizeMode("LongFormat",1);
	public final static mdEmail.StandardizeMode AutoFormat=new mdEmail.StandardizeMode("AutoFormat",2);

	private final String enumName;
	private final int enumValue;
	private static StandardizeMode[] enumValues={ShortFormat,LongFormat,AutoFormat};

	private StandardizeMode(String name,int val) {
		enumName=name;
		enumValue=val;
	}

	public static StandardizeMode toEnum(int val) {
		for (int i=0;i<enumValues.length;i++)
			if (enumValues[i].enumValue==val)
				return enumValues[i];
		throw new IllegalArgumentException("No enum "+StandardizeMode.class+" with value "+val+".");
	}

	public String toString() {
		return enumName;
	}

	public int toValue() {
		return enumValue;
	}
}

public final static class SuiteParseMode {
	public final static mdEmail.SuiteParseMode ParseSuite=new mdEmail.SuiteParseMode("ParseSuite",0);
	public final static mdEmail.SuiteParseMode CombineSuite=new mdEmail.SuiteParseMode("CombineSuite",1);

	private final String enumName;
	private final int enumValue;
	private static SuiteParseMode[] enumValues={ParseSuite,CombineSuite};

	private SuiteParseMode(String name,int val) {
		enumName=name;
		enumValue=val;
	}

	public static SuiteParseMode toEnum(int val) {
		for (int i=0;i<enumValues.length;i++)
			if (enumValues[i].enumValue==val)
				return enumValues[i];
		throw new IllegalArgumentException("No enum "+SuiteParseMode.class+" with value "+val+".");
	}

	public String toString() {
		return enumName;
	}

	public int toValue() {
		return enumValue;
	}
}

public final static class AliasPreserveMode {
	public final static mdEmail.AliasPreserveMode ConvertAlias=new mdEmail.AliasPreserveMode("ConvertAlias",0);
	public final static mdEmail.AliasPreserveMode PreserveAlias=new mdEmail.AliasPreserveMode("PreserveAlias",1);

	private final String enumName;
	private final int enumValue;
	private static AliasPreserveMode[] enumValues={ConvertAlias,PreserveAlias};

	private AliasPreserveMode(String name,int val) {
		enumName=name;
		enumValue=val;
	}

	public static AliasPreserveMode toEnum(int val) {
		for (int i=0;i<enumValues.length;i++)
			if (enumValues[i].enumValue==val)
				return enumValues[i];
		throw new IllegalArgumentException("No enum "+AliasPreserveMode.class+" with value "+val+".");
	}

	public String toString() {
		return enumName;
	}

	public int toValue() {
		return enumValue;
	}
}

public final static class AutoCompletionMode {
	public final static mdEmail.AutoCompletionMode AutoCompleteSingleSuite=new mdEmail.AutoCompletionMode("AutoCompleteSingleSuite",0);
	public final static mdEmail.AutoCompletionMode AutoCompleteRangedSuite=new mdEmail.AutoCompletionMode("AutoCompleteRangedSuite",1);
	public final static mdEmail.AutoCompletionMode AutoCompletePlaceHolderSuite=new mdEmail.AutoCompletionMode("AutoCompletePlaceHolderSuite",2);
	public final static mdEmail.AutoCompletionMode AutoCompleteNoSuite=new mdEmail.AutoCompletionMode("AutoCompleteNoSuite",3);

	private final String enumName;
	private final int enumValue;
	private static AutoCompletionMode[] enumValues={AutoCompleteSingleSuite,AutoCompleteRangedSuite,AutoCompletePlaceHolderSuite,AutoCompleteNoSuite};

	private AutoCompletionMode(String name,int val) {
		enumName=name;
		enumValue=val;
	}

	public static AutoCompletionMode toEnum(int val) {
		for (int i=0;i<enumValues.length;i++)
			if (enumValues[i].enumValue==val)
				return enumValues[i];
		throw new IllegalArgumentException("No enum "+AutoCompletionMode.class+" with value "+val+".");
	}

	public String toString() {
		return enumName;
	}

	public int toValue() {
		return enumValue;
	}
}

public final static class ResultCdDescOpt {
	public final static mdEmail.ResultCdDescOpt ResultCodeDescriptionLong=new mdEmail.ResultCdDescOpt("ResultCodeDescriptionLong",0);
	public final static mdEmail.ResultCdDescOpt ResultCodeDescriptionShort=new mdEmail.ResultCdDescOpt("ResultCodeDescriptionShort",1);

	private final String enumName;
	private final int enumValue;
	private static ResultCdDescOpt[] enumValues={ResultCodeDescriptionLong,ResultCodeDescriptionShort};

	private ResultCdDescOpt(String name,int val) {
		enumName=name;
		enumValue=val;
	}

	public static ResultCdDescOpt toEnum(int val) {
		for (int i=0;i<enumValues.length;i++)
			if (enumValues[i].enumValue==val)
				return enumValues[i];
		throw new IllegalArgumentException("No enum "+ResultCdDescOpt.class+" with value "+val+".");
	}

	public String toString() {
		return enumName;
	}

	public int toValue() {
		return enumValue;
	}
}

public final static class MailboxLookupMode {
	public final static mdEmail.MailboxLookupMode MailboxNone=new mdEmail.MailboxLookupMode("MailboxNone",0);
	public final static mdEmail.MailboxLookupMode MailboxExpress=new mdEmail.MailboxLookupMode("MailboxExpress",1);
	public final static mdEmail.MailboxLookupMode MailboxPremium=new mdEmail.MailboxLookupMode("MailboxPremium",2);

	private final String enumName;
	private final int enumValue;
	private static MailboxLookupMode[] enumValues={MailboxNone,MailboxExpress,MailboxPremium};

	private MailboxLookupMode(String name,int val) {
		enumName=name;
		enumValue=val;
	}

	public static MailboxLookupMode toEnum(int val) {
		for (int i=0;i<enumValues.length;i++)
			if (enumValues[i].enumValue==val)
				return enumValues[i];
		throw new IllegalArgumentException("No enum "+MailboxLookupMode.class+" with value "+val+".");
	}

	public String toString() {
		return enumName;
	}

	public int toValue() {
		return enumValue;
	}
}

	protected mdEmail(long i,boolean own) {
		ownMemory=own;
		I=i;
	}

	public mdEmail() {
		this(mdEmailJNI.mdEmailCreate(),true);
	}

	public synchronized void delete() {
		if (I!=0) {
			if (ownMemory) {
				ownMemory=false;
				mdEmailJNI.mdEmailDestroy(I);
			}
			I=0;
		}
	}

	public boolean SetLicenseString(String License) {
		return mdEmailJNI.SetLicenseString(I,License);
	}

	public void SetPathToEmailFiles(String emailDataFiles) {
		mdEmailJNI.SetPathToEmailFiles(I,emailDataFiles);
	}

	public ProgramStatus InitializeDataFiles() {
		return ProgramStatus.toEnum(mdEmailJNI.InitializeDataFiles(I));
	}

	public String GetInitializeErrorString() {
		return mdEmailJNI.GetInitializeErrorString(I);
	}

	public String GetBuildNumber() {
		return mdEmailJNI.GetBuildNumber(I);
	}

	public String GetDatabaseDate() {
		return mdEmailJNI.GetDatabaseDate(I);
	}

	public String GetDatabaseExpirationDate() {
		return mdEmailJNI.GetDatabaseExpirationDate(I);
	}

	public String GetLicenseStringExpirationDate() {
		return mdEmailJNI.GetLicenseStringExpirationDate(I);
	}

	public boolean VerifyEmail(String email) {
		return mdEmailJNI.VerifyEmail(I,email);
	}

	public void SetCorrectSyntax(boolean p1) {
		mdEmailJNI.SetCorrectSyntax(I,p1);
	}

	public void SetUpdateDomain(boolean p1) {
		mdEmailJNI.SetUpdateDomain(I,p1);
	}

	public void SetDatabaseLookup(boolean p1) {
		mdEmailJNI.SetDatabaseLookup(I,p1);
	}

	public void SetFuzzyLookup(boolean p1) {
		mdEmailJNI.SetFuzzyLookup(I,p1);
	}

	public void SetWSLookup(boolean p1) {
		mdEmailJNI.SetWSLookup(I,p1);
	}

	public void SetWSMailboxLookup(mdEmail.MailboxLookupMode mailboxLookupmode) {
		mdEmailJNI.SetWSMailboxLookup(I,mailboxLookupmode.toValue());
	}

	public void SetMXLookup(boolean p1) {
		mdEmailJNI.SetMXLookup(I,p1);
	}

	public void SetStandardizeCasing(boolean p1) {
		mdEmailJNI.SetStandardizeCasing(I,p1);
	}

	public String GetStatusCode() {
		return mdEmailJNI.GetStatusCode(I);
	}

	public String GetErrorCode() {
		return mdEmailJNI.GetErrorCode(I);
	}

	public String GetErrorString() {
		return mdEmailJNI.GetErrorString(I);
	}

	public long GetChangeCode() {
		return mdEmailJNI.GetChangeCode(I);
	}

	public String GetResults() {
		return mdEmailJNI.GetResults(I);
	}

	public String GetResultCodeDescription(String resultCode, mdEmail.ResultCdDescOpt opt) {
		return mdEmailJNI.GetResultCodeDescription(I,resultCode,opt.toValue());
	}

	public String GetMailBoxName() {
		return mdEmailJNI.GetMailBoxName(I);
	}

	public String GetDomainName() {
		return mdEmailJNI.GetDomainName(I);
	}

	public String GetTopLevelDomain() {
		return mdEmailJNI.GetTopLevelDomain(I);
	}

	public String GetTopLevelDomainDescription() {
		return mdEmailJNI.GetTopLevelDomainDescription(I);
	}

	public String GetEmailAddress() {
		return mdEmailJNI.GetEmailAddress(I);
	}

	public void SetReserved(String Property, String Value_) {
		mdEmailJNI.SetReserved(I,Property,Value_);
	}

	public String GetReserved(String Property_) {
		return mdEmailJNI.GetReserved(I,Property_);
	}

	public void SetCachePath(String cachePath) {
		mdEmailJNI.SetCachePath(I,cachePath);
	}

	public void SetCacheUse(int cacheUse) {
		mdEmailJNI.SetCacheUse(I,cacheUse);
	}

}
