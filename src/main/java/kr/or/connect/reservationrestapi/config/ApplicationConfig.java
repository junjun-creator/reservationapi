package kr.or.connect.reservationrestapi.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import kr.or.connect.reservationrestapi.config.DBConfig;

@Configuration
@ComponentScan(basePackages = {"kr.or.connect.reservationrestapi.dao", "kr.or.connect.reservationrestapi.service"})
@Import({DBConfig.class})
public class ApplicationConfig {

}
