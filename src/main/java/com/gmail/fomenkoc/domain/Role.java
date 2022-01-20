package com.gmail.fomenkoc.domain;

import java.util.Objects;

public class Role {

	private Integer roleID;
	private String roleName;
	private Boolean isStaff;
	

	public Role(Integer roleID, String roleName, Boolean isStaff) {
		super();
		this.roleID = roleID;
		this.roleName = roleName;
		this.isStaff = isStaff;
	}

	public Role(String roleName) {
		super();
		this.roleName = roleName;
		this.isStaff = false;
	}

	public Integer getRoleID() {
		return roleID;
	}

	public void setRoleID(Integer roleID) {
		this.roleID = roleID;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Boolean getIsStaff() {
		return isStaff;
	}

	public void setIsStaff(Boolean isStaff) {
		this.isStaff = isStaff;
	}

	@Override
	public int hashCode() {
		return Objects.hash(isStaff, roleID, roleName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		return Objects.equals(isStaff, other.isStaff)
				&& Objects.equals(roleID, other.roleID)
				&& Objects.equals(roleName, other.roleName);
	}

	@Override
	public String toString() {
		return "Role [roleID=" + roleID + ", roleName=" + roleName
				+ ", isStaff=" + isStaff + "]";
	}



}
