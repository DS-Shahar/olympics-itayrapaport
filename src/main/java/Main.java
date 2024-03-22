
public class Athlete {
    private String name;
    private Grade[] grades;
    private int count;

    // פעולה בונה
    public Athlete(String name, int maxGrades) {
        this.name = name;
        this.grades = new Grade[maxGrades];
        this.count = 0;
    }

    // פעולה בונה מעתיקה
    public Athlete(Athlete other) {
        this.name = other.name;
        this.count = other.count;
        this.grades = new Grade[other.grades.length];
        for (int i = 0; i < this.count; i++) {
            this.grades[i] = new Grade(other.grades[i].getApparatus(), other.grades[i].getScore());
        }
    }

    // פעולה להוספת ציון
    public boolean addGrade(String apparatus, double score) {
        if (count < grades.length) {
            grades[count] = new Grade(apparatus, score);
            count++;
            return true;
        }
        return false;
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Grade[] getGrades() {
		return grades;
	}

	public void setGrades(Grade[] grades) {
		this.grades = grades;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	// פעולה למחיקת ציון
    public boolean deleteGrade(String apparatus) {
        for (int i = 0; i < count; i++) {
            if (grades[i].getApparatus().equals(apparatus)) {
                grades[i] = null; 
                count--;   
                return true;
            }
        }
        return false;
    }
    //פעולה לחישוב ממוצע
    public double average() {
        if (count == 0) {
            return -1;
        }
        double sum = 0;
        int actualCount = 0; 
        for (int i = 0; i < count; i++) {
            if (grades[i] != null) {
                sum += grades[i].getScore();
                actualCount++;
            }
        }
        if (actualCount == 0) {
            return -1; 
        }
        return sum / actualCount;
    }


    //
    public boolean allGradesAbove(double number) {
        for (int i = 0; i < count; i++) {
            if (grades[i].getScore() <= number) {
                return false;
            }
        }
        return true;
    }

    // 
    public boolean isBetter(Athlete other) {
        for (int i = 0; i < count; i++) {
            boolean found = false;
            for (int j = 0; j < other.count; j++) {
                if (grades[i].getApparatus().equals(other.grades[j].getApparatus())) {
                    found = true;
                    if (grades[i].getScore() <= other.grades[j].getScore()) {
                        return false;
                    }
                }
            }
            if (!found) { 
                return false;
            }
        }
        return true;
    }
}
public class Grade {
	private String apparatus;
	private double score;	
	public Grade(String apparatus, double score) {
		this.apparatus = apparatus;
		this.score = score;
	}
	public String getApparatus() {
		return apparatus;
	}
	public void setApparatus(String apparatus) {
		this.apparatus = apparatus;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	
}
public class Group {
    private String name;
    private Athlete[] athletes;
    private int count; // משתנה לספירת מספר האתלטים בקבוצה

  
    public Group(String name, int size) {
        this.name = name;
        this.athletes = new Athlete[size];
        this.count = 0;
    }

    
    public boolean addAthlete(Athlete athlete) {
        if (count < athletes.length) {
            athletes[count++] = athlete;
            return true;
        }
        return false;
    }


    public boolean deleteAthlete(String name) {
        for (int i = 0; i < count; i++) {
            if (athletes[i].getName().equals(name)) {
                athletes[i] = null; 
                return true;
            }
        }
        return false;
    }

  
    public String getBestAthleteName() {
        double highestAverage = 0;
        String bestAthleteName = null;
        for (int i = 0; i < athletes.length; i++) {
            if (athletes[i] != null) {
                double average = athletes[i].average();
                if (average > highestAverage) {
                    highestAverage = average;
                    bestAthleteName = athletes[i].getName();
                }
            }
        }
        return bestAthleteName;
    }

    // הוספת ציון לאתלט
    public boolean addGrade(String athleteName, String apparatus, double score) {
        for (int i = 0; i < count; i++) {
            if (athletes[i].getName().equals(athleteName)) {
                return athletes[i].addGrade(apparatus, score);
            }
        }
        return false;
    }
}
public class Main1 {

		public static void main(String[] args) {
	        
	        Athlete athlete1 = new Athlete("Alice", 5);
	        Athlete athlete2 = new Athlete("Bob", 5);

	        // הוספת ציונים לספורטאיות
	        athlete1.addGrade("Floor", 15.0);
	        athlete1.addGrade("Vault", 14.5);
	        athlete1.addGrade("Beam", 16.0);

	        athlete2.addGrade("Floor", 14.0);
	        athlete2.addGrade("Vault", 15.0);
	        athlete2.addGrade("Beam", 15.5);

	        
	        System.out.println("Average score of " + athlete1.getName() + ": " + athlete1.average());
	        System.out.println("Average score of " + athlete2.getName() + ": " + athlete2.average());

	        // 
	        System.out.println(athlete1.getName() + " all grades above 14.0? " + athlete1.allGradesAbove(14.0));
	        System.out.println(athlete2.getName() + " all grades above 15.0? " + athlete2.allGradesAbove(15.0));

	      
	        System.out.println(athlete1.getName() + " is better than " + athlete2.getName() + "? " + athlete1.isBetter(athlete2));

	      
	        athlete1.deleteGrade("Vault");
	        System.out.println("After deleting Vault, " + athlete1.getName() + "'s average: " + athlete1.average());

	        
	        System.out.println(athlete1.getName() + "'s remaining grades:");
	        for (int i = 0; i < athlete1.getCount(); i++) {
	            Grade grade = athlete1.getGrades()[i];
	            if (grade != null) {
	                System.out.println(grade.getApparatus() + ": " + grade.getScore());
	            }
	        }
		
	}

}
public class Main2 {
    public static void main(String[] args) {
        
        Group group = new Group("Gymnastics Team", 5);

        
        Athlete athlete1 = new Athlete("Alice", 10);
        Athlete athlete2 = new Athlete("Bob", 10);

        
        group.addAthlete(athlete1);
        group.addAthlete(athlete2);

        
        group.addGrade("Alice", "Floor", 10);
        group.addGrade("Alice", "Bea	m", 9.4);
        group.addGrade("Bob", "Floor", 9.4);
        group.addGrade("Bob", "Beam", 9.8);

      
        String bestAthlete = group.getBestAthleteName();
        System.out.println("Best athlete in the group is: " + bestAthlete);

        
        group.deleteAthlete("Alice");
        bestAthlete = group.getBestAthleteName();
        System.out.println("After deletion, best athlete in the group is: " + bestAthlete);
    }
}
