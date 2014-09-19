package org.avcoe.data;

/**
 * @author Paras Waykole
 */

public class Information {

		Information()
		{	
		}
		
		private static Information INSTANCE=new Information();

		public static Information getInstance(){
			return INSTANCE;
		}
		
		public String getInfo(int no)
		{
			String s ="";
			
			switch(no)
			{
				case 0:
					s = "Sun is the star at the center of the Solar System. It is almost perfectly "
							+ "spherical and consists of hot plasma interwoven with magnetic fields."
							+ "The sun formed about 4.567 billion years ago from the gravitational collapse of a region "
							+ "within a large molecular cloud."
							+ "\n\n Radius: 696342km"
							+ "\n Mass: 1.98855x10^30kg"
							+ "\n Surface Area: 6.09x10^12 Sq.km"
							+ "\n Temperature: 1.57x10^7K";
					break;
				case 1:
					s = "Mercury is the smallest and closest to the Sun of the eight planets in the Solar System,"
							+" with an orbital period of about 88 Earth days. Seen from Earth, it appears "
							+"to move around its orbit in about 116 days, which is much faster than any other planet. "
							+"It has no known natural satellites. The planet is named after "
							+"the Roman deity Mercury, the messenger to the gods."
							+"\n\n Radius: 2439.7km"
							+"\n Mass: 3.3022×10^23 kg"
							+"\n Revolution Period: 88 days"
							+ "\n Temperature: 100K to 700K";
					break;
				case 2:
					s = "Venus is the second planet from the Sun, orbiting "
							+ "it every 224.7 Earth days. It has no natural satellite."
							+ " It is named after the Roman goddess of love and beauty."
							+ "\n\n Radius: 6051.8km"
							+ "\n Mass: 4.8676×10^24 kg"
							+ "\n Revolution Period: 225 days"
							+ "\n Temperature: 737K";
					break;
				case 3:
					s = "Earth is the third planet from the Sun, the densest planet in the Solar System"
							+ "and the only celestial body known to accommodate life. The Moon is Earth's only natural satellite."
							+ "\n\n Radius: 6371km"
							+ "\n Mass: 5.97219×10^24 kg"
							+ "\n Revolution Period: 365 days"
							+ "\n Temperature:184K to 330K";
					break;
				case 4:
					s = "Mars is the fourth planet from the Sun and the second smallest planet in the Solar System, "
							+ "after Mercury. Named after the Roman god of war, it is often described as the 'Red Planet'"
							+ " because the iron oxide prevalent on its surface gives it a reddish appearance."
							+ "\n\n Radius: 3389.5km"
							+ "\n Mass: 6.4185×10^23kg"
							+ "\n Revolution Period: 687 days"
							+ "\n Temperature: 130K to 308K";
					break;
				case 5:
					s = "Jupiter is the fifth planet from the Sun and the largest planet in the Solar System."
							+ "The Romans named the planet after the Roman god Jupiter."
							+ "Jupiter has the largest planetary atmosphere in the Solar System, spanning over 5,000 km (3,107 mi) in altitude."
							+ "\n\n Radius: 69911km"
							+ "\n Mass: 1.8986×10^27 kg"
							+ "\n Revolution Period: 12 years"
							+ "\n Temperature: 165K";
					break;
				case 6:
					s = "Saturn is the sixth planet from the Sun and the second largest planet in the Solar System, after Jupiter. Named after the Roman god of agriculture."
							+ "Saturn has a prominent ring system that consists of nine continuous main rings and three discontinuous arcs, composed mostly of "
							+ "ice particles with a smaller amount of rocky debris and dust."
							+ "\n\n Radius: 58232km"
							+ "\n Mass: 5.6846×10^26kg"
							+ "\n Revolution Period: 29 years"
							+ "\n Temperature: 134K";
					break;
				case 7:
					s = "Uranus is the seventh planet from the Sun. It has the third-largest planetary radius and fourth-largest planetary mass in the Solar System."
							+ "It is the only planet whose name is derived from a figure from Greek mythology rather than Roman mythology like the other planets, from "
							+ "the Latinized version of the Greek god of the sky, Ouranos.  Uranus has a ring system, a magnetosphere, and numerous moons."
							+ "\n\n Radius: 25362km"
							+ "\n Mass: 8.6810×10^25 kg"
							+ "\n Revolution Period: 84 years"
							+ "\n Temperature: 76K";
					break;
				case 8:
					s = "Neptune is the eighth and farthest planet from the Sun in the Solar System. It is the fourth-largest planet by diameter and the third-largest by mass."
							+ " Named after the Roman god of the sea."
							+ "Neptune was the first planet found by mathematical prediction rather than by empirical observation."
							+ "\n\n Radius: 24622km"
							+ "\n Mass: 1.0243×10^26 kg"
							+ "\n Revolution Period: 165 years"
							+ "\n Temperature: 72K";
					break;
				default:
					s = "Something Went Worng";
			}
			
			return s;
		}
		

	
}
