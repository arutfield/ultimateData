package data.raw;

import enums.RawDataEnums;

public class Weather {
		private final Double temperature;
		private final Double windSpeed;
		private final Double windDirection;
		private final Double precipitation;
		private final RawDataEnums.YesNoNA anyPrecipitation;

		public Weather(Double temperature, Double windSpeed, Double windDirection, Double precipitation,
				RawDataEnums.YesNoNA anyPrecipitation) {
			this.temperature = temperature;
			this.windSpeed = windSpeed;
			this.windDirection = windDirection;
			this.precipitation = precipitation;
			this.anyPrecipitation = anyPrecipitation;
		}

		public Double getTemperature() {
			return temperature;
		}

		public Double getWindSpeed() {
			return windSpeed;
		}

		public Double getWindDirection() {
			return windDirection;
		}

		public Double getPrecipitation() {
			return precipitation;
		}

		public RawDataEnums.YesNoNA getAnyPrecipitation() {
			return anyPrecipitation;
		}

}
