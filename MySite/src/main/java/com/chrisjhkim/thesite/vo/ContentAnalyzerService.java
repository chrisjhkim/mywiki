package com.chrisjhkim.thesite.vo;

import java.util.ArrayList;

public interface ContentAnalyzerService {
	// Content -> Name Analyze Unit List
	public ArrayList<ContentNameAnalyzedUnit> getNameAnalyzeUnitListFromContent(Content content);
	
	// Name List -> result
	public ContentNameAnalyzedResult getContentNameAnazyedResult(ArrayList<ContentNameAnalyzedUnit> contentAnalyzedNameUnitList);

	
	public ArrayList<ContentCupAnalyzedUnit> getCupAnalyzeUnitListFromContent(Content content);
	public ContentCupAnalyzedResult getContentCupAnalyzedResult(ArrayList<ContentCupAnalyzedUnit> contentCupAnalyzedUnitList);
	
	
}
