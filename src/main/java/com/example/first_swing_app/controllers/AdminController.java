package com.example.first_swing_app.controllers;

import com.example.first_swing_app.dto.CreateDeviceDto;
import com.example.first_swing_app.dto.DevicesByLocationDto;
import com.example.first_swing_app.dto.UpdateDeviceDto;
import com.example.first_swing_app.mapper.MeteringDeviceMapper;
import com.example.first_swing_app.models.Location;
import com.example.first_swing_app.models.MeteringDevice;
import com.example.first_swing_app.models.User;
import com.example.first_swing_app.models.UserRole;
import com.example.first_swing_app.security.jwt.JwtTokenManager;
import com.example.first_swing_app.security.service.UserService;
import com.example.first_swing_app.services.AdminService;
import com.example.first_swing_app.services.LocationService;
import com.example.first_swing_app.services.MeteringDeviceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final UserService userService;
    private final LocationService locationService;
    private final MeteringDeviceService meteringDeviceService;
    private final JwtTokenManager jwtTokenManager;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(adminService.getAllUsers());
    }

    @PostMapping("/user")
    public ResponseEntity<User> upsertUser(@RequestBody User user) {
        return ResponseEntity.ok(adminService.addUser(user));
    }

    @DeleteMapping("/user/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        adminService.deleteUser(userId);
    }

    @PostMapping("/devices")
    public ResponseEntity<List<MeteringDevice>> getAllDevices(@RequestBody String token) {
        if(userService.findByUsername(jwtTokenManager.getUsernameFromToken(token)).getUserRole() != UserRole.ADMIN) {
            return ResponseEntity.status(403).body(null);
        }

        return ResponseEntity.ok(meteringDeviceService.getAll());
    }

    @PostMapping("/location/{locationId}/devices")
    public ResponseEntity<List<MeteringDevice>> getAllDevicesForLocation(@PathVariable Long locationId, @RequestBody DevicesByLocationDto request) {
        if(userService.findByUsername(jwtTokenManager.getUsernameFromToken(request.getToken())).getUserRole() != UserRole.ADMIN) {
            return ResponseEntity.status(403).body(null);
        }

        return ResponseEntity.ok(meteringDeviceService.getAllForLocation(locationId));
    }

    @PostMapping("/user/{userId}/devices")
    public ResponseEntity<List<MeteringDevice>> getAllDevicesForUser(@PathVariable Long userId, @RequestBody DevicesByLocationDto request) {
        if(userService.findByUsername(jwtTokenManager.getUsernameFromToken(request.getToken())).getUserRole() != UserRole.ADMIN) {
            return ResponseEntity.status(403).body(null);
        }
        return ResponseEntity.ok(meteringDeviceService.getAllForUser(userId));
    }

    @PostMapping("/device")
    public ResponseEntity<MeteringDevice> createDevice(@RequestBody CreateDeviceDto createDeviceRequest) {
        MeteringDevice meteringDevice = MeteringDeviceMapper.INSTANCE.convertToMeteringDevice(createDeviceRequest);
        return ResponseEntity.ok(this.meteringDeviceService.saveMeteringDevice(meteringDevice));
    }

    @PostMapping("/device/{deviceId}")
    public ResponseEntity<MeteringDevice> updateDevice(@PathVariable Long deviceId, @RequestBody UpdateDeviceDto updateDeviceRequest) {
        Location location = locationService.getLocationForId(updateDeviceRequest.getLocationId());
        User user = userService.findById(updateDeviceRequest.getUserId());
        MeteringDevice meteringDevice = new MeteringDevice(deviceId, updateDeviceRequest.getName(), updateDeviceRequest.getDescription(), location, updateDeviceRequest.getMaximumHourlyConsumption(), user);
        return ResponseEntity.ok(this.meteringDeviceService.saveMeteringDevice(meteringDevice));
    }

    @DeleteMapping("/device/{deviceId}")
    public ResponseEntity<ResponseBody> updateDevice(@PathVariable Long deviceId) {
        this.meteringDeviceService.deleteMeteringDevice(deviceId);
        return ResponseEntity.ok(null);
    }
}
