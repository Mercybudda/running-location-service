package demo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
// DB -> Entity
//Entity -> DB
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Entity
public class Location {
  enum GpsStatus{
      EXCELLENT, OK, UNRELIABLE, BAD, NOFIX, UNKNOWN;
  }
  public enum RunnerMovementType{
      STOPPED, IN_MOTION;
      public boolean isMoving(){
          return this != STOPPED;
      }
  }
  @Id
  @GeneratedValue
  private Long id;

  @Embedded
  @AttributeOverride(name = "bandMake", column = @Column(name = "unit_band_make"))
  private final UnitInfo unitInfo;

  @Embedded
  @AttributeOverrides({
          @AttributeOverride(name = "fmi", column = @Column(name = "medical_fmi")),
          @AttributeOverride(name = "bfr", column = @Column(name = "medical_bfr"))
  })
  private MedicalInfo medicalInfo;

  private double latitude;
  private double longitude;

  private String heading;
  private double gpsSpeed;
  private GpsStatus gpsStatus;

  private double odometer;
  private double totalRunningTime;
  private double totalIdleTime;
  private double totalCaloriesBurnt;
  private String address;
  private Date timestamp = new Date();
  private String gearProvider;
  private RunnerMovementType runnerMovementType = RunnerMovementType.STOPPED;
  private String serviceType;

  public Location(){this.unitInfo = null;}

  @JsonCreator
  public Location(@JsonProperty("runningId") String runningId){
      this.unitInfo = new UnitInfo(runningId);
  }
  public Location(UnitInfo unitInfo){
    this.unitInfo = unitInfo;
  }

  public String getRunningId(){
    return this.unitInfo == null? null : this.unitInfo.getRunningId();
  }

  public String getAddress(){
     return address;
  }

}
