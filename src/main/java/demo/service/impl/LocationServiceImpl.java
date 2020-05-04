package demo.service.impl;

import demo.domain.Location;
import demo.domain.LocationRepository;
import demo.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // indicate it is a demo.domain.service!!!
public class LocationServiceImpl implements LocationService {
    private LocationRepository locationRepository;

    @Autowired  // spring data generate an instance and inject in, autowire locationRepository into the
    //locationserviceimpl!!!
    public LocationServiceImpl(LocationRepository locationRepository){
        this.locationRepository = locationRepository;
    }
    @Override
    public List<Location> saveRunningLocations(List<Location> runningLocations) {

        return locationRepository.saveAll(runningLocations);
    }

    @Override
    public void deleteAll() {
        locationRepository.deleteAll();
    }

    @Override
    public Page<Location> findByRunnerMovementType(String movementType, Pageable pageable) {
        return locationRepository.findByRunnerMovementType(Location.RunnerMovementType.valueOf(movementType), pageable);
    }
}
