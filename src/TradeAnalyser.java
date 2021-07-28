import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class TradeAnalyser {
	/**
	 * Read the stock data from a csv file, then return an ArrayList containing
	 * StockSnapshot objects generated from the data in the csv (each entry in the
	 * ArrayList corresponds to a line in the csv file)
	 * 
	 * @param infile the String representing the name of the file to be opened
	 * @throws IOException if file is not found
	 * @return an arraylist containing StockSnapshot objects 
	 */
	public static ArrayList<TradeSnapshot> readStockData(String infile) throws IOException{
		ArrayList<TradeSnapshot> stock = new ArrayList<>();
		BufferedReader in = new BufferedReader(new FileReader(infile));
		
		String s;
		s = in.readLine(); // skip the first line, i.e the CSV headers
		while ((s = in.readLine()) != null) {
			
			// extract the rest of the data (comma separated)
			String[] row = s.split(",",-1);
			
			// first column of tickers
			String ticker = null;
			try {
				ticker = row[0];
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// second column of dates
			String date = null;
			try {
				date = row[1];
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// third column of values
			String name = null;
			try {
				name = row[2];
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// fourth column of values
			String direction = null;
			try {
				direction = row[3];
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// fifth column of values
			Integer quantity = null;
			try {
				quantity = Integer.parseInt(row[4]);
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// sixth column of values
			Double price = null;
			try {
				price = Double.parseDouble(row[5]);
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// seventh column of values
			String currency = null;
			try {
				currency = row[6];
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// eighth column of values
			Double consideration = null;
			try {
				consideration = Double.parseDouble(row[7]);
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// ninth column of values
			Double commission = null;
			try {
				commission = Double.parseDouble(row[8]);
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// tenth column of values
			Double exchangeRate = null;
			try {
				exchangeRate = Double.parseDouble(row[9]);
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// create a new Snapshot
			stock.add(new TradeSnapshot(ticker, date, name, direction, quantity, price, currency, consideration, commission, exchangeRate));
		}
		return stock;
	}
	
	private static ArrayList<TradeSnapshot> combineTrades(ArrayList<TradeSnapshot> trades) {
		
		ArrayList<TradeSnapshot> combinedTrades = new ArrayList<>();
		System.out.println(trades.size());
		
		for (TradeSnapshot trade : trades) {
		        if(combinedTrades.stream().anyMatch(o -> o.getName().equals(trade.getName()))) {
		        	// found same name
		        	// combine with existing item
		        	for (TradeSnapshot tradeCombined : trades) {
		        		if (tradeCombined.getName().equals(trade.getName())) {
		        			try {
		        				
		        			} catch (Exception ex) {
					        	tradeCombined.setQuantity(Integer.valueOf(tradeCombined.getQuantity() + trade.getQuantity()));
					        	tradeCombined.setConsideration(tradeCombined.getConsideration() + trade.getConsideration());
					        	tradeCombined.setCommission(tradeCombined.getCommission() + trade.getCommission());
		        			}
		        		}
		        	}
		        } else {
		        	// new trade name
		        	// add to combined list
		        	combinedTrades.add(trade);
		        }
		}
		
		return combinedTrades;
	}
	
	// simulate buying and selling in order to find capital gains
	private static ArrayList<TradeSnapshot> simulateBuySell(ArrayList<TradeSnapshot> trades) {
		return null;
	}
	
	private static Double findCapitalGains(ArrayList<TradeSnapshot> trades) {
		for (TradeSnapshot trade : trades) {
			
		}
		return 0d;
	}
	
	public static void main(String[] args) {
		
		ArrayList<TradeSnapshot> trades = new ArrayList<>();
		
		try {	
			trades = readStockData("src\\TradeHistory-data.csv");
		}
		catch (IOException e) {
			System.out.println("Exception: " + e);
		}
		
		/*
		ArrayList<TradeSnapshot> combinedTrades = combineTrades(trades);
		
		for(TradeSnapshot trade : combinedTrades) {
			System.out.println(trade.getName() + ", ");
			System.out.println(trade.getConsideration() + ", ");
			System.out.println("-");
		}
		*/
		
		// check if the stock data was read correctly
		/*
		System.out.println(trades.size());
		System.out.println(trades.get(0).getName());
		System.out.println(trades.get(0).getDate());
		System.out.println(trades.get(0).getConsideration());
		*/
	}
}
