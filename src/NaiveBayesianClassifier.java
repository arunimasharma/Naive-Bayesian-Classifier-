import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


public class NaiveBayesClassifier {

	public NaiveBayesClassifier() {
		
	}
	
	public int lines;
	public int columns;
	public float prob[][][];
	public int y;
	public float probY1;
	public float probY0;
	
	public void Training(String path){
		
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(path)));
			
			columns= Integer.parseInt(reader.readLine());
			lines = Integer.parseInt(reader.readLine());
			
			prob = new float[columns][2][2];
			y=0;
			int i = 0;
			while(i < lines){
				String InputLines = reader.readLine();
				
				String Inputs[] = InputLines.split(":");
				String XValues[] = Inputs[0].split(" ");
				Inputs[1] = Inputs[1].trim();
				int YValue = Integer.parseInt(Inputs[1]);
				
				
				
				if (YValue == 1){
					y++;
					int j=0;
					while(j < columns){
						int X = Integer.parseInt(XValues[j]); 
						if (X==0){
							prob[j][YValue][0]++;
						}
						else {
							prob[j][YValue][1]++;
						}
						j++;
					}
				}
				
				else {
					int j=0;
					while(j < columns){
						int X = Integer.parseInt(XValues[j]); 
						if (X==0){
							prob[j][YValue][0]++;
						}
						else {
							prob[j][YValue][1]++;
						}
						j++;
					}
				}
				
				i++;
			}
			
		      int l=0;
			  while(l < columns) {
				  
				 //When YValue=1
				prob[l][1][0]= prob[l][1][0]/lines;
				prob[l][1][1]= prob[l][1][1]/lines;
				
				//When YValue=0
				prob[l][0][0]= prob[l][0][0]/lines;
				prob[l][0][1]= prob[l][0][1]/lines;
				
				l++;
			}
			  
			//Probabilities of y=1 and y=1
				probY1 = (float)y/lines;
				int y0= lines-y;
				probY0 = (float)y0/lines;
			
		}
		
		catch(Exception exp){
			exp.printStackTrace();
		}
	}
	
	public void Predicting(String path){
		
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(path)));
			
			reader.readLine();
			int predictLines = Integer.parseInt(reader.readLine());
			
			int i = 0;
			int goodpredict =0;
			float percentgood;
			while(i < predictLines){
				String InputLines = reader.readLine();
				
				String Inputs[] = InputLines.split(":");
				String XValues[] = Inputs[0].split(" ");
				Inputs[1] = Inputs[1].trim();
				int YValue = Integer.parseInt(Inputs[1]);
				float pY0 ;
				float pY1;
				
				pY1=1;
				pY0=1;
			
				int j=0;
				
				while(j < columns){
					int X = Integer.parseInt(XValues[j]);
					if (X==1){
						pY0 = pY0*(prob[j][0][1])/probY0;
					}
					else {
						pY0 = pY0*(prob[j][0][0])/probY0;
					}
					j++;
				}
			
				
				j=0;
				while(j < columns){
					int X = Integer.parseInt(XValues[j]);
					if (X==1){
						pY1 = pY1*(prob[j][1][1])/probY1;
					}
					else {
						pY1 = pY1*(prob[j][1][0])/probY1;
					}
					j++;
				}
				
					
			
				
				if (pY0*probY0 > pY1*probY1){
					//System.out.println("Y=0 is the prediction!");
					if (YValue==0){
						goodpredict++;
					}
				}
				else {
					//System.out.println("Y=1 is the prediction!");
					if (YValue==1){
						goodpredict++;
					}	
				}
				
				
			i++;
			}
			
			percentgood = 100*(((float)goodpredict)/predictLines);
			System.out.println("Percentage of good predictions are:");
			System.out.println(percentgood);
		}
		
		catch(Exception exp){
			exp.printStackTrace();
		}
	}	
	
public void TrainingLaplace(String path){
		
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(path)));
			
			columns= Integer.parseInt(reader.readLine());
			lines = Integer.parseInt(reader.readLine());
			
			prob = new float[columns][2][2];
			y=2;
			int i = 0;
			
			for (int k = 0; k < columns; k++){
				prob[k][0][0] = 1;
				prob[k][0][1] = 1;
				prob[k][1][0] = 1;
				prob[k][1][1] = 1;
			}
			
			while(i < lines){
				String InputLines = reader.readLine();
				
				String Inputs[] = InputLines.split(":");
				String XValues[] = Inputs[0].split(" ");
				Inputs[1] = Inputs[1].trim();
				int YValue = Integer.parseInt(Inputs[1]);
				
				
				
				if (YValue == 1){
					y++;
					int j=0;
					while(j < columns){
						int X = Integer.parseInt(XValues[j]); 
						if (X==0){
							prob[j][YValue][0]++;
						}
						else {
							prob[j][YValue][1]++;
						}
						j++;
					}
				}
				
				else {
					int j=0;
					while(j < columns){
						int X = Integer.parseInt(XValues[j]); 
						if (X==0){
							prob[j][YValue][0]++;
						}
						else {
							prob[j][YValue][1]++;
						}
						j++;
					}
				}
				
				i++;
			}
			
		      int l=0;
			  while(l < columns) {
				  
				 //When YValue=1
				  int newlines = lines + 4;
				prob[l][1][0]= prob[l][1][0]/newlines;
				prob[l][1][1]= prob[l][1][1]/newlines;
				
				//When YValue=0
				prob[l][0][0]= prob[l][0][0]/newlines;
				prob[l][0][1]= prob[l][0][1]/newlines;
				
				l++;
			}
			  
			//Probabilities of y=1 and y=1
			  int newlines = lines + 4;
			probY1 = (float)y/newlines;
				int y0= newlines-y;
				probY0 = (float)y0/newlines;
			
		}
		
		catch(Exception exp){
			exp.printStackTrace();
		}
	}
          
	
	public static void main (String[] args){
		NaiveBayesClassifier test = new NaiveBayesClassifier();
		
		test.Training("InputFiles\\simple-train.txt");
		test.Predicting("InputFiles\\simple-test.txt");
		
		test.TrainingLaplace("InputFiles\\simple-train.txt");
		test.Predicting("InputFiles\\simple-test.txt");
		
		test.Training("InputFiles\\heart-train.txt");
		test.Predicting("InputFiles\\heart-test.txt");
		
		test.TrainingLaplace("InputFiles\\heart-train.txt");
		test.Predicting("InputFiles\\heart-test.txt");
		
		test.Training("InputFiles\\vote-train.txt");
		test.Predicting("InputFiles\\vote-test.txt");
		
		test.TrainingLaplace("InputFiles\\vote-train.txt");
		test.Predicting("InputFiles\\vote-test.txt");
		
		
		
	}

}
