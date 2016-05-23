package ee.kaarelkivistik.webarchitecture;

import ee.kaarelkivistik.webarchitecture.models.Device;
import ee.kaarelkivistik.webarchitecture.models.DeviceType;
import ee.kaarelkivistik.webarchitecture.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by kaarel on 22.05.16.
 */

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    DeviceRepository deviceRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        deviceRepository.findAll().forEach(device -> {
            System.out.println(device.toString());
        });
    }
}
