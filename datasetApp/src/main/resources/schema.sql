DROP TABLE IF EXISTS Daw_Jones_Index;
  
CREATE TABLE Daw_Jones_Index (
 id INT AUTO_INCREMENT  PRIMARY KEY,
 quarter INT,
 stock VARCHAR(250),
 date VARCHAR(250),
 open VARCHAR(250),
 high VARCHAR(250),
 low VARCHAR(250),
 close VARCHAR(250),
 volume BIGINT,
 percent_change_price DOUBLE,
 percent_change_volume_over_last_wk DOUBLE,
 previous_weeks_volume BIGINT,
 next_weeks_open VARCHAR(250),
 next_weeks_close VARCHAR(250),
 percent_change_next_weeks_price DOUBLE,
 days_to_next_dividend INT,
 percent_return_next_dividend DOUBLE
);