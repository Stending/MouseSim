package utilities;

/**
 * This class is used to locate a square of the grid
 * @author Rova
 *
 */
	public class Point {
	
		private int x;
		private int y;
		
		public Point () {
			this(0,0);
		}
		
		public Point(int x, int y) {
			this.x=x;
			this.y=y;
		}
		
		public void setX(int x) {
			this.x = x;
		}
		
		public void setY(int y) {
			this.y = y;
		}
		
		public int getX() {
			return this.x;
		}	
		
		public int getY() {
			return this.y;
		}
		public double distanceWith(Point p) {
    		return Math.sqrt(Math.pow(p.getX()-this.getX(), 2) + Math.pow(p.getY()-this.getY(), 2));
    	}
		public boolean equals(Point p){
			if (p == null)
				return false;
			else
				return (this.x == p.getX() && this.y == p.getY());
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Point other = (Point) obj;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}

		public String toString() {
			return "(" + this.x + "," + this.y + ")";
		}
		
		
}
