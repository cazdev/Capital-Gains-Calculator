import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

public class TradeAnalyser {
	/**
	 * Read the stock data from a csv file, then return an ArrayList containing
	 * TradeSnapshot objects generated from the data in the csv (each entry in the
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
				price = Double.valueOf(row[5]);
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
				consideration = Double.valueOf(row[7]);
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// ninth column of values
			Double commission = null;
			try {
				commission = Double.valueOf(row[8]);
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// tenth column of values
			Double exchangeRate = null;
			try {
				exchangeRate = Double.valueOf(row[9]);
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
			
	        if (trade.getName().equals("Tesla Motors Inc (All Sessions)")) {
	        	System.out.println(trade.getConsideration());
	        }
			
		        if(combinedTrades.stream().anyMatch(o -> o.getName().equals(trade.getName()))) {
		        	// found same name
		        	// combine with existing item
		        	for (TradeSnapshot tradeCombined : combinedTrades) {
		        		if (tradeCombined.getName().equals(trade.getName())) {
		        			
					        tradeCombined.setQuantity(Integer.valueOf(tradeCombined.getQuantity() + trade.getQuantity()));
					        tradeCombined.setConsideration(tradeCombined.getConsideration() + trade.getConsideration());
					        
					        if (trade.getName().equals("Tesla Motors Inc (All Sessions)")) {
					        	System.out.println(tradeCombined.getConsideration() + " + " + trade.getConsideration() + " = " + tradeCombined.getConsideration() + trade.getConsideration());
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
	
	private static ArrayList<TradeSnapshot> convertTradesToAUD(ArrayList<TradeSnapshot> trades) {
		for (TradeSnapshot trade : trades) {
			if (trade.getCurrency().equals("USD")) {
				trade.setConsideration(trade.getConsideration() * trade.getExchangeRate());
				trade.setPrice(trade.getPrice() * trade.getExchangeRate());
			}
		}
		return trades;
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
		
		// Order by oldest date first
		Collections.reverse(trades);
		
		// convert to AUD
		trades = convertTradesToAUD(trades);
		
		ArrayList<TradeSnapshot> combinedTrades = combineTrades(trades);
		
		Double total = 0d;
		for(TradeSnapshot trade : combinedTrades) {
			if (trade.getQuantity() == 0) {
				System.out.println(trade.getName() + ", ");
				System.out.println(trade.getConsideration() + ", ");
				System.out.println("-");	
				total += trade.getConsideration();
			}
		}
		System.out.print("TOTAL P&L: ");
		System.out.println(total);
		
		
		
		// check if the stock data was read correctly
		/*
		System.out.println(trades.size());
		System.out.println(trades.get(0).getName());
		System.out.println(trades.get(0).getDate());
		System.out.println(trades.get(0).getConsideration());
		*/
	}
}
