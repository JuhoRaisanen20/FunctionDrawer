import java.awt.Graphics;
import java.awt.Color;
import javax.swing.*;

public class DrawArea extends JPanel {
	
	private int pixelkoko = 1;
	private int leveys = 1200;
	private int korkeus = 800;
	private int rangeMin = -2;
	private int rangeMax = 2;
	private float hue = 0.0f, sat = 0.75f, bright = 1.0f;
	
	private int funktionPituus;
	private int maxPituus = 50;
	private double [] kertoimet = new double[maxPituus];
	private int kerroin = 0;
	
	private int [] funktionOsat = new int[maxPituus];
	
	
	public DrawArea() {
		int luku = setFunktio("x2+y2");
	}
	
	public int getRangeMin() {
		return rangeMin;
	}
	public int getRangeMax() {
		return rangeMax;
	}
	public float getHue() {
		return hue;
	}
	public float getSat() {
		return sat;
	}
	public float getBright() {
		return bright;
	}
	
	public void setRangeMin(int rangeMin) {
		this.rangeMin = rangeMin;
	}
	public void setRangeMax(int rangeMax) {
		this.rangeMax = rangeMax;
	}
	public void setHue(float hue) {
		this.hue = hue;
	}
	public void setSat(float sat) {
		this.sat = sat;
	}
	public void setBright(float bright) {
		this.bright = bright;
	}
	
	public int setFunktio(String funktio) {
		
		tyhjennaTaulukot();
		kasitteleSyote(funktio);
		int tulos = tarkistaFunktio();
		
		if(tulos == 1) {
			repaint();
		}
		
		return tulos;
	}
	
	private void tyhjennaTaulukot() {
		int i;
		for(i = 0; i < maxPituus; i++) {
			kertoimet[i] = 0;
			funktionOsat[i] = 0;
		}
		funktionPituus = 0;
		kerroin = 0;
	}
	
	private int tarkistaFunktio() {
		int i, k, input, sulkuja;
		int tulos = 1;
		
		for(i = 0; i < funktionPituus; i++) {
			
			input = funktionOsat[i];
			
			switch(input) {
				case 0:
				i = funktionPituus;
				tulos = 0;
				break;
				
				case 1:
				sulkuja = 1;
				for(k = i+1; k < funktionPituus; k++) {
					
					if(funktionOsat[k] == 1) {
						sulkuja++;
					}
					if(funktionOsat[k] == 2) {
						sulkuja--;
						if(sulkuja == 0) {
							k = funktionPituus;
						}
					}
				}
				if(sulkuja != 0) {
					tulos = 0;
					i = funktionPituus;
				}
				break;
				
				case 2:
				break;
				
				case 3:
				if(funktionOsat[i+1] < 10) {
					if(funktionOsat[i+1] != 1) {
						tulos = 0;
						i = funktionPituus;	
					}
				}
				break;
				
				case 4:
				if(funktionOsat[i+1] < 10) {
					if(funktionOsat[i+1] != 1) {
						tulos = 0;
						i = funktionPituus;	
					}
				}
				break;
				
				case 5:
				if(funktionOsat[i+1] < 10) {
					if(funktionOsat[i+1] != 1) {
						tulos = 0;
						i = funktionPituus;	
					}
				}
				break;
				
				case 6:
				if(funktionOsat[i+1] < 10) {
					if(funktionOsat[i+1] != 1) {
						tulos = 0;
						i = funktionPituus;	
					}
				}
				break;
				
				case 12:
				if(funktionOsat[i+1] != 1) {
					tulos = 0;
					i = funktionPituus;
				}
				break;
				
				case 13:
				if(funktionOsat[i+1] != 1) {
					tulos = 0;
					i = funktionPituus;
				}
				break;
				
				case 14:
				if(funktionOsat[i+1] != 1) {
					tulos = 0;
					i = funktionPituus;
				}
				break;
			}
		}
		
		if(funktionPituus == 0) {
			tulos = 0;
		}
		return tulos;
	}
	
	private void kasitteleSyote(String funktio) {
		
		int i, k, c=0;
		int max = funktio.length();
		int [] kirjaimet = new int[max];
		
		for(i = 0; i < max; i++) {
			c = (int) funktio.charAt(i);
			kirjaimet[i] = c;
		}
		
		
		
		for(i = 0; i < max; i++) {
			
			c = kirjaimet[i];
			
			//c == numero
			if(c >= 48 && c <= 57) {
				for(k = i; k < max; k++) {
					c = kirjaimet[k];
					
					if(c < 48 || c > 57) {
						kertoimet[kerroin] = Integer.parseInt(funktio.substring(i, k));
						kerroin++;
						
						i = k-1;
						k = max;
					}
					if(k == max-1) {
						kertoimet[kerroin] = Integer.parseInt(funktio.substring(i, k+1));
						kerroin++;
						
						i = k;
						k = max;						
					}
				}
				
			//c == x
			} else if (c == 88 || c == 120) {
				if(i-1 >= 0) {
					c = kirjaimet[i-1];
					if(c < 48 || c > 57) {
						kertoimet[kerroin] = 1;
						kerroin++;
					}
				} else { 
					kertoimet[kerroin] = 1;
					kerroin++;
				}
				
				if(i+1 < max) {
					c = kirjaimet[i+1];
					if(c < 48 || c > 57) {
						kertoimet[kerroin] = 1;
						kerroin++;
					}
				} else { 
					kertoimet[kerroin] = 1;
					kerroin++;
				}
				
				funktionOsat[funktionPituus] = 10;
				funktionPituus++;
				//c == y
			} else if (c == 89 || c == 121) {
				
				if(i-1 >= 0) {
					c = kirjaimet[i-1];
					if(c < 48 || c > 57) {
						kertoimet[kerroin] = 1;
						kerroin++;
					}
				} else { 
					kertoimet[kerroin] = 1;
					kerroin++;
				}
				
				if(i+1 < max) {
					c = kirjaimet[i+1];
					if(c < 48 || c > 57) {
						kertoimet[kerroin] = 1;
						kerroin++;
					}
				} else { 
					kertoimet[kerroin] = 1;
					kerroin++;
				}	
				
				funktionOsat[funktionPituus] = 11;
				funktionPituus++;
				
				
				//c == sin
			} else if (c == 83 || c == 115) {	
			
				if(i < max-2) {
					
					if(kirjaimet[i+1] == 73 || kirjaimet[i+1] == 105) {
						if(kirjaimet[i+2] == 78 || kirjaimet[i+2] == 110) {
							if(i-1 >= 0) {
								c = kirjaimet[i-1];
								if(c < 48 || c > 57) {
									kertoimet[kerroin] = 1;
									kerroin++;
								}
							} else { 
								kertoimet[kerroin] = 1;
								kerroin++;
							}
							
							funktionOsat[funktionPituus] = 12;
							funktionPituus++;
							i += 2;
						}
					}
				}
			
			
			
				//c == cos
			} else if (c == 67 || c == 99) {	
			
				if(i < max-2) {
					
					if(kirjaimet[i+1] == 79 || kirjaimet[i+1] == 111) {
						if(kirjaimet[i+2] == 83 || kirjaimet[i+2] == 115) {
							if(i-1 >= 0) {
								c = kirjaimet[i-1];
								if(c < 48 || c > 57) {
									kertoimet[kerroin] = 1;
									kerroin++;
								}
							} else { 
								kertoimet[kerroin] = 1;
								kerroin++;
							}
							
							funktionOsat[funktionPituus] = 13;
							funktionPituus++;
							i += 2;
						}
					}
				}
				
				//c == tan
			} else if (c == 84 || c == 116) {	
			
				if(i < max-2) {
					
					if(kirjaimet[i+1] == 65 || kirjaimet[i+1] == 97) {
						if(kirjaimet[i+2] == 78 || kirjaimet[i+2] == 110) {
							if(i-1 >= 0) {
								c = kirjaimet[i-1];
								if(c < 48 || c > 57) {
									kertoimet[kerroin] = 1;
									kerroin++;
								}
							} else { 
								kertoimet[kerroin] = 1;
								kerroin++;
							}
							
							funktionOsat[funktionPituus] = 14;
							funktionPituus++;
							i += 2;
						}
					}
				}
				
				//c == (
			} else if (c == 40) {
				
				funktionOsat[funktionPituus] = 1;
				funktionPituus++;
				// c== )
			} else if (c == 41) {
				
				funktionOsat[funktionPituus] = 2;
				funktionPituus++;
				// c == *
			} else if (c == 42) {
				
				funktionOsat[funktionPituus] = 5;
				funktionPituus++;
				//c == +
			} else if (c == 43) {
				
				funktionOsat[funktionPituus] = 3;
				funktionPituus++;
				//c == -
			} else if (c == 45) {
		
				funktionOsat[funktionPituus] = 4;
				funktionPituus++;
				//c == /
			} else if (c == 47) {
				
				funktionOsat[funktionPituus] = 6;
				funktionPituus++;
			}
			
			
		}
	}
	
	private double laskeOsaArvo(int x, int y, int alku, int loppu) {
		
		int i, k, input, sulkuja;
		double tulos = 0;
		
		for(i = alku; i < loppu; i++) {
			
			input = funktionOsat[i];
			
			switch(input) {
				
				// (
				case 1:
				sulkuja = 1;
				for(k = i+1; k < loppu; k++) {
					
					if(funktionOsat[k] == 1) {
						sulkuja++;
					}
					if(funktionOsat[k] == 2) {
						sulkuja--;
						if(sulkuja == 0) {
							tulos += laskeOsaArvo(x, y, i+1, k);
							i = k;
							k = loppu;
						}
					}
				}
				break;
				
				// )
				case 2:
				break;
				
				// +
				case 3:
				tulos += laskeOsaArvo(x, y, i+1, loppu);
				i = loppu;
				break;
				
				// -
				case 4:
				tulos -= laskeOsaArvo(x, y, i+1, loppu);
				i = loppu;
				break;
				
				// *
				case 5:
				if(funktionOsat[i+1] == 1) {
					sulkuja = 1;
					for(k = i+2; k < loppu; k++) {
						
						if(funktionOsat[k] == 1) {
							sulkuja++;
						}
						if(funktionOsat[k] == 2) {
							sulkuja--;
							if(sulkuja == 0) {
								tulos *= laskeOsaArvo(x, y, i+2, k);
								i = k;
								k = loppu;
							}
						}
					}
				} else {
					tulos *= laskeOsaArvo(x, y, i+1, loppu);
					i = loppu;
				}
				break;
				
				// /
				case 6:
				if(funktionOsat[i+1] == 1) {
					sulkuja = 1;
					for(k = i+2; k < loppu; k++) {
						
						if(funktionOsat[k] == 1) {
							sulkuja++;
						}
						if(funktionOsat[k] == 2) {
							sulkuja--;
							if(sulkuja == 0) {
								double arvo = laskeOsaArvo(x, y, i+2, k);
								if(arvo != 0) { tulos = tulos/arvo; }
								else {tulos = -1000000; }
								i = k;
								k = loppu;
							}
						}
					}
				} else {
					double arvo = laskeOsaArvo(x, y, i+1, loppu);
					if(arvo != 0) { tulos = tulos/arvo; }
					else {tulos = -1000000; }
					i = loppu;
				}
				break;
				
				case 10:
				tulos = kertoimet[kerroin]*Math.pow((double) x, kertoimet[kerroin+1]);
				kerroin += 2;
				break;
				
				case 11:
				tulos = kertoimet[kerroin]*Math.pow((double) y, kertoimet[kerroin+1]);
				kerroin += 2;
				break;
				
				case 12:
				sulkuja = 1;
				for(k = i+2; k < loppu; k++) {
					
					if(funktionOsat[k] == 1) {
						sulkuja++;
					}
					if(funktionOsat[k] == 2) {
						sulkuja--;
						if(sulkuja == 0) {
							double a = kertoimet[kerroin];
							kerroin++;
							tulos = a*Math.sin(laskeOsaArvo(x, y, i+2, k));
							i = k;
							k = loppu;
						}
					}
				}
				break;
				
				case 13:
				sulkuja = 1;
				for(k = i+2; k < loppu; k++) {
					
					if(funktionOsat[k] == 1) {
						sulkuja++;
					}
					if(funktionOsat[k] == 2) {
						sulkuja--;
						if(sulkuja == 0) {
							double a = kertoimet[kerroin];
							kerroin++;
							tulos = a*Math.cos(laskeOsaArvo(x, y, i+2, k));
							i = k;
							k = loppu;
						}
					}
				}
				break;
				
				case 14:
				sulkuja = 1;
				for(k = i+2; k < loppu; k++) {
					
					if(funktionOsat[k] == 1) {
						sulkuja++;
					}
					if(funktionOsat[k] == 2) {
						sulkuja--;
						if(sulkuja == 0) {
							double a = kertoimet[kerroin];
							kerroin++;
							tulos = a*Math.tan(laskeOsaArvo(x, y, i+2, k));
							i = k;
							k = loppu;
						}
					}
				}
				break;
			}
		}
		
		return tulos;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, leveys, korkeus);
		
		int xx, yy, a, b;
		double arvo, osaArvo;
		for(xx = 0; xx < leveys; xx += pixelkoko) {
			
			for (yy = 0; yy < korkeus; yy += pixelkoko) {
				
				a = xx - (leveys/2);
				b = yy - (korkeus/2);
				
				kerroin = 0;
				osaArvo = laskeOsaArvo(a, b, 0, funktionPituus);
				arvo = (double) (osaArvo - rangeMin)/(rangeMax-rangeMin);
				
				if(arvo > 1) {arvo = 1;}
				if(arvo < 0) {arvo = 0;}
				
				bright = (float) arvo;
				g.setColor(Color.getHSBColor(hue, sat, bright));
				g.fillRect(xx, yy, pixelkoko, pixelkoko);
			}
		}
		
		String teksti1 = new String();
		String teksti2 = new String();
		int i, luku;
		double luku2;
		for(i = 0; i < funktionPituus; i++) {
			luku = funktionOsat[i];
			teksti1 += Integer.toString(luku);
			teksti1 += " ";
		}
		
		for(i = 0; i < maxPituus; i++) {
			luku2 = kertoimet[i];
			
			if(luku2 != 0) {
				teksti2 += Double.toString(luku2);
				teksti2 += " ";
			}
		}
		g.setColor(Color.white);
		g.drawString(teksti1, leveys-200, 20);
		g.drawString(teksti2, leveys-200, 40);
	}	
}