/**
 * The MIT License
 * Copyright © 2017 DTL
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package nl.dtls.fairdatapoint.database.mongo.migration.production;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import nl.dtls.fairdatapoint.Profiles;
import org.bson.Document;
import org.springframework.context.annotation.Profile;

import java.util.List;

@ChangeLog(order = "0004")
@Profile(Profiles.PRODUCTION)
public class Migration_0004_ResourceDefinition {

    @ChangeSet(order = "0004", id = "Migration_0004_ResourceDefinition", author = "migrationBot")
    public void run(MongoDatabase db) {
        migrateResourceDefinitions(db);
    }

    private void migrateResourceDefinitions(MongoDatabase db) {
        MongoCollection<Document> rdCol = db.getCollection("resourceDefinition");
        rdCol.deleteMany(new Document());
        rdCol.insertOne(repositoryDefinition());
        rdCol.insertOne(catalogDefinition());
        rdCol.insertOne(datasetDefinition());
        rdCol.insertOne(distributionDefinition());
    }

    private Document repositoryDefinition() {
        Document definition = new Document();
        definition.append("uuid", "77aaad6a-0136-4c6e-88b9-07ffccd0ee4c");
        definition.append("name", "Repository");
        definition.append("urlPrefix", "");
        definition.append("specs", "https://www.purl.org/fairtools/fdp/schema/0.1/fdpMetadata");
        definition.append("targetClassUris", List.of("http://www.w3.org/ns/dcat#Resource",
                "http://www.re3data.org/schema/3-0#Repository"));

        // Child
        Document child = new Document();
        child.append("resourceDefinitionUuid", "a0949e72-4466-4d53-8900-9436d1049a4b");
        child.append("relationUri", "http://www.re3data.org/schema/3-0#dataCatalog");
        Document listView = new Document();
        listView.append("title", "Catalogs");
        listView.append("tagsUri", "http://www.w3.org/ns/dcat#themeTaxonomy");
        listView.append("metadata", List.of());
        child.append("listView", listView);
        definition.append("child", child);

        // Parent
        definition.append("parent", null);

        // External Links
        definition.append("externalLinks", List.of());

        definition.append("_class", "nl.dtls.fairdatapoint.entity.resource.ResourceDefinition");
        return definition;
    }

    private Document catalogDefinition() {
        Document definition = new Document();
        definition.append("uuid", "a0949e72-4466-4d53-8900-9436d1049a4b");
        definition.append("name", "Catalog");
        definition.append("urlPrefix", "catalog");
        definition.append("specs", "https://www.purl.org/fairtools/fdp/schema/0.1/catalogMetadata");
        definition.append("targetClassUris", List.of("http://www.w3.org/ns/dcat#Resource",
                "http://www.w3.org/ns/dcat#Catalog"));

        // Child
        Document child = new Document();
        child.append("resourceDefinitionUuid", "2f08228e-1789-40f8-84cd-28e3288c3604");
        child.append("relationUri", "http://www.w3.org/ns/dcat#dataset");
        Document listView = new Document();
        listView.append("title", "Datasets");
        listView.append("tagsUri", "http://www.w3.org/ns/dcat#theme");
        listView.append("metadata", List.of());
        child.append("listView", listView);
        definition.append("child", child);

        // Parent
        Document parent = new Document();
        parent.append("resourceDefinitionUuid", "77aaad6a-0136-4c6e-88b9-07ffccd0ee4c");
        definition.append("parent", parent);

        // External Links
        definition.append("externalLinks", List.of());

        definition.append("_class", "nl.dtls.fairdatapoint.entity.resource.ResourceDefinition");
        return definition;
    }

    private Document datasetDefinition() {
        Document definition = new Document();
        definition.append("uuid", "2f08228e-1789-40f8-84cd-28e3288c3604");
        definition.append("name", "Dataset");
        definition.append("urlPrefix", "dataset");
        definition.append("specs", "https://www.purl.org/fairtools/fdp/schema/0.1/datasetMetadata");
        definition.append("targetClassUris", List.of("http://www.w3.org/ns/dcat#Resource",
                "http://www.w3.org/ns/dcat#Dataset"));

        // Child
        Document child = new Document();
        child.append("resourceDefinitionUuid", "02c649de-c579-43bb-b470-306abdc808c7");
        child.append("relationUri", "http://www.w3.org/ns/dcat#distribution");
        // - list View
        Document listView = new Document();
        listView.append("title", "Distributions");
        listView.append("tagsUri", null);
        // - metadata
        Document metadata = new Document();
        metadata.append("title", "Media Type");
        metadata.append("propertyUri", "http://www.w3.org/ns/dcat#mediaType");
        listView.append("metadata", List.of(metadata));
        child.append("listView", listView);
        definition.append("child", child);

        // Parent
        Document parent = new Document();
        parent.append("resourceDefinitionUuid", "a0949e72-4466-4d53-8900-9436d1049a4b");
        definition.append("parent", parent);

        // External Links
        definition.append("externalLinks", List.of());

        definition.append("_class", "nl.dtls.fairdatapoint.entity.resource.ResourceDefinition");
        return definition;
    }

    private Document distributionDefinition() {
        Document definition = new Document();
        definition.append("uuid", "02c649de-c579-43bb-b470-306abdc808c7");
        definition.append("name", "Distribution");
        definition.append("urlPrefix", "distribution");
        definition.append("specs", "https://www.purl.org/fairtools/fdp/schema/0.1/distributionMetadata");
        definition.append("targetClassUris", List.of("http://www.w3.org/ns/dcat#Resource",
                "http://www.w3.org/ns/dcat#Distribution"));

        // Child
        definition.append("child", null);

        // Parent
        Document parent = new Document();
        parent.append("resourceDefinitionUuid", "2f08228e-1789-40f8-84cd-28e3288c3604");
        definition.append("parent", parent);

        // External Links
        Document accessLink = new Document();
        accessLink.append("title", "Access online");
        accessLink.append("propertyUri", "http://www.w3.org/ns/dcat#accessURL");
        Document downloadLink = new Document();
        downloadLink.append("title", "Download");
        downloadLink.append("propertyUri", "http://www.w3.org/ns/dcat#downloadURL");
        definition.append("externalLinks", List.of(accessLink, downloadLink));

        definition.append("_class", "nl.dtls.fairdatapoint.entity.resource.ResourceDefinition");
        return definition;
    }

}