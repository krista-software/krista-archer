/*
 * Archer Extension for Krista
 * Copyright (C) 2025 Krista Software
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package app.krista.extension.cyber_security.compliance.impl.model;

import com.google.gson.annotations.SerializedName;

public class UpdateInformation {


    @SerializedName("CreateDate")
    private String createDate;

    @SerializedName("UpdateDate")
    private String updateDate;

    @SerializedName("CreateLogin")
    private Integer createLogin;

    @SerializedName("UpdateLogin")
    private Integer updateLogin;

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getCreateLogin() {
        return createLogin;
    }

    public void setCreateLogin(Integer createLogin) {
        this.createLogin = createLogin;
    }

    public Integer getUpdateLogin() {
        return updateLogin;
    }

    public void setUpdateLogin(Integer updateLogin) {
        this.updateLogin = updateLogin;
    }

}
