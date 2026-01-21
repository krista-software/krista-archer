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

package app.krista.extension.cyber_security.compliance.catalog.entities;


import app.krista.extension.impl.anno.Domain;
import app.krista.extension.impl.anno.Entity;
import app.krista.extension.impl.anno.Field;
import app.krista.extension.impl.anno.Searchable;
import app.krista.extension.impl.anno.ToString;

@Domain(id = "catEntryDomain_c54a8d90-5657-4586-bf45-ce2252889764",
        name = "Compliance",
        ecosystemId = "catEntryEcosystem_5516afe8-3809-4918-b911-d89597fc96e0",
        ecosystemName = "Cyber Security",
        ecosystemVersion = "2b68e0e2-78d0-4d12-be6b-bd8a74ee1e0b")
@Entity(name = "Questionnaire", id = "localDomainEntity_8860470e-bd7b-457a-b0fb-9f114b6f1346", primaryKey = "Id", supportStore = false, options = {})
@Searchable
public class Questionnaire {

    @Searchable
    @ToString
    @Field.Text(name = "Id", required = true, attributes = {}, options = {})
    public String id;

    @Searchable
    @ToString
    @Field.Text(name = "Type", required = true, attributes = {}, options = {})
    public String type;

    @Searchable
    @ToString
    @Field.Text(name = "Name", required = true, attributes = {}, options = {})
    public String name;

    @Searchable
    @ToString
    @Field.Text(name = "Alias", required = true, attributes = {}, options = {})
    public String alias;

    @Searchable
    @ToString
    @Field.Text(name = "Guid", required = true, attributes = {}, options = {})
    public String guid;
}
