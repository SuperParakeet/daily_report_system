package actions.views;

import models.Follow;

public class FollowConverter {

    public static Follow toModel(FollowView rv) {
        return new Follow(
                rv.getId(),
                EmployeeConverter.toModel(rv.getEmployee()),
                EmployeeConverter.toModel(rv.getEmployeeFollow()),
                rv.getDeleteFlag(),
                rv.getCreatedAt(),
                rv.getUpdatedAt());
    }
}
