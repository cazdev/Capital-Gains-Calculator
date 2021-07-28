
public class TradeSnapshot {
		private String ticker;
		private String date;
		private String name;
		private String direction;
		private Integer quantity;
		private Double price;
		private String currency;
		private Double consideration;
		private Double commission;
		private Double exchangeRate;
		
		public TradeSnapshot(String ticker, String date, String name, String direction, Integer quantity, Double price, String currency, Double consideration, Double commission, Double exchangeRate) {
			this.ticker = ticker;
			this.date = date;
			this.name = name;
			this.direction = direction;
			this.quantity = quantity;
			this.price = price;
			this.currency = currency;
			this.consideration = consideration;
			this.exchangeRate = exchangeRate;
		}

		public String getDate() {
			return date;
		}

		public Double getPrice() {
			return price;
		}

		public String getName() {
			return name;
		}
		
		public String getDirection() {
			return direction;
		}

		public Double getCommission() {
			return commission;
		}

		public String getCurrency() {
			return currency;
		}

		public Double getConsideration() {
			return consideration;
		}

		public Integer getQuantity() {
			return quantity;
		}

		public Double getExchangeRate() {
			return exchangeRate;
		}

		public String getTicker() {
			return ticker;
		}
		
		public void setPrice(Double price) {
			this.price = price;
		}
		
		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}

		public void setConsideration(Double consideration) {
			this.consideration = consideration;
		}

		public void setCommission(Double commission) {
			this.commission = commission;
		}
}
