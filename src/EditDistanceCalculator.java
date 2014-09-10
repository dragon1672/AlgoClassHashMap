
public class EditDistanceCalculator {
	public int editDistance(String a, String b) {
		return editDistanceRecursive(a, b);
	}
	
	private int editDistanceHelper(String a, String b, int[][] eds) {
		for ( int i = 1; i <= a.length(); i++ ) {
			eds[i][0] = b.length();
		}
		for ( int j = 1; j <= b.length(); j++ ) {
			eds[0][j] = a.length();
		}
		
		for ( int i = 1; i <= a.length(); i++ ) {
			for ( int j = 1;  j <= b.length(); j++ ) {
				int delete = eds[i-1][j] + 1;
				int insert = eds[i][j-1] + 1;
				int remove = eds[i-1][j-1] + (a.charAt(i-1) == b.charAt(j-1) ? 0 : 1);
				eds[i][j] = Math.min(delete, Math.min(insert, remove));
			}
		}
		
		return eds[a.length()][b.length()];
	}
	
	/**
	 * #2.  Add a recursive version here, which uses a memory function.  Change your internal implementation
	 * to use this version. (1 point)
	 */
	private int editDistanceRecursive(String a, String b) {
		return editDistanceRecursiveHelper(a,b,a.length(),b.length(),new Integer[a.length()+1][b.length()+1]);
	}
	private int editDistanceRecursiveHelper(String a, String b, int i , int j, Integer[][] eds) {
		if(j == 0) {
			return  i > 0 ? eds[i][j] = b.length() : 0;
		}
		if(i == 0) {
			return j > 0 ? eds[i][j] = a.length() : 0;
		}
		if(eds[i][j] != null) return eds[i][j];
		int delete = editDistanceRecursiveHelper(a,b,i-1,j,eds) + 1;
		int insert = editDistanceRecursiveHelper(a,b,i,j-1,eds) + 1;
		int remove = editDistanceRecursiveHelper(a,b,i-1,j-1,eds) + (a.charAt(i-1) == b.charAt(j-1) ? 0 : 1);
		return eds[i][j] = Math.min(delete, Math.min(insert, remove));
	}
}
