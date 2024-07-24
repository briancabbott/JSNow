# Publishing to Azure

This tutorial walks you through the task of creating a simple Node.js application and publishing it to Azure.

When publishing a Node.js application to Azure, there are several options. These include Azure App Service, a VM running an OS of your choosing, Azure Container Service (AKS) for management with Kubernetes, a Container Instance using Docker, and more. For more details on each of these options, see [Compute](https://azure.microsoft.com/en-us/product-categories/compute/).

For this tutorial, you deploy the app to [Linux App Service](https://docs.microsoft.com/en-us/azure/app-service/containers/app-service-linux-intro).
Linux App Service deploys a Linux Docker container to run the Node.js application (as opposed to the Windows App Service, which runs Node.js apps behind IIS on Windows).

This tutorial shows how to create a Node.js application starting from a template installed with the Node.js Tools for Visual Studio, push the code to a repository on GitHub, and then provision an Azure App Service via the Azure web portal so that you can deploy from the GitHub repository. To use the command-line to provision the Azure App Service and push the code from a local Git repository, see [Create Node.js App](https://docs.microsoft.com/en-us/azure/app-service/containers/quickstart-nodejs).

The following task are required to get this scenario up and running:

* The Node.js project to run in the Azure App Service
* A GitHub repository to push the code to
* Azure App Service set up to host the project

The details for each of these tasks follows.

## Create a Node.js project to run in Azure

1. Create a new TypeScript Express app using the **File** > **New Project** dialog box.

    <img width="940px" src="../../images/linux-appservice/ts-express-app.png"/>

2. Press **F5** to build and run the app, and make sure that everything is running as expected.

3. Select **File** > **Add to source control** to create a local Git repository for the project.

    At this point, a Node.js app using the Express framework and written in TypeScript is working and checked in to local source control.

4. Edit the project as desired before proceeding to the next steps.

## Push code from Visual Studio to GitHub

To set up GitHub for Visual Studio:

1. Make sure the [GitHub Extension for Visual Studio](https://visualstudio.github.com/) is installed and enabled using the
menu item **Tools** > **Extensions and Updates**.

2. From the menu select **View** > **Other Windows** > **GitHub**, and the following appears.

    <img width="382px" src="../../images/linux-appservice/github-get-started.png"/>

3. Click **Get started**.

    If you are already connected to GitHub, the toolbox appears similar
to the following illustration.

    <img width="382px" src="../../images/linux-appservice/github-publish.png"/>

4. Fill out the fields for the new repository to publish, and then click **Publish**.

    After a few moments, a banner stating "Repository created successfully"  appears.

The next steps show you how to publish from this repository to an Azure App Service on Linux.

## Create a Linux App Service in Azure

1. Log in to the [Azure portal](https://portal.azure.com).

2. Select **App Services** from the list of services on the left, and then click **Add**.

3. If required, create a new Resource Group and App Service plan to host the new app.

4. Make sure to set the **OS** to **Linux**, and set **Runtime Stack** to the required Node.js version, as shown in the illustration.

<img width="618px" src="../../images/linux-appservice/create-appservice-annotated.png"/>

5. Click **Create** to create the App Service. It may take a few minutes to deploy.

6. Once created, go to the **Application settings** section, and add a setting with a name of `SCM_SCRIPT_GENERATOR_ARGS` and a value of `--node`.

    <img width="575px" src="../../images/linux-appservice/script-generator-args.png"/>

> :warning:
> The App Service deployment process uses a set of heuristics to determine which type of application to try and run. If a **.sln** file is detected in the deployed content, it will assume an MSBuild based project is being deployed. The setting added above overrides this logic and specifies explicitly that this is a Node.js application. Without this setting, the Node.js application will fail to deploy if the *.sln* file is part of the repository being deployed to the App Service.

7. Once created, open the App Service and select **Deployment options**.

    <img width="250px" src="../../images/linux-appservice/deployment-options.png"/>

8. Click **Choose source**, and then choose **GitHub**, and then configure any required permissions.

    <img width="635px" src="../../images/linux-appservice/choose-source.png"/>

9. Select the repository and branch to publish, and then select **OK**.

    <img width="450px" src="../../images/linux-appservice/repo-and-branch.png"/>

    The **deployment options** page appears as shown while syncing. Once it is finished, a check mark will appear.

    <img width="575px" src="../../images/linux-appservice/deployment-options-sync.png"/>

    The site is now running the Node.js application from the GitHub repository,
and it is accessible at the URL created for the Azure App Service (by default the name given to the Azure App Service followed by ".azurewebsites.net").

### Push additional changes

1. Add the code shown here in *app.ts* after the line `app.use('/users', users);`. This adds a REST API at the URL */api*.

    ```typescript
    app.use('/api', (req, res, next) => {
        res.json({"result": "success"});
    });
    ```

2. Build the code and test it locally, then check it in and push to GitHub.

    In the Azure portal, the changes at the GitHub repo will be detected in a few moments, and a new sync of the deployment will start. This looks similar to the following illustration.

    <img width="594px" src="../../images/linux-appservice/changes-detected.png"/>

3. Once deployment is complete, navigate to the public site and append */api* to the URL. The JSON response gets returned.

## Notes

* If the node.exe process dies (that is, an unhandled exception occurs), the container restarts.
* When the container starts up, it runs through various heuristics to figure out
how to start the Node.js process. Details of the implementation can be seen at
[generateStartupCommand.js](https://github.com/Azure-App-Service/node/blob/master/8.9.4/startup/generateStartupCommand.js).
* You can connect to the running container via SSH for investigations. This is easily done using the Azure Portal. Select the App Service, and scroll down
the list of tools until reaching **SSH** under the **Development Tools** section.
* To aid in troubleshooting, go to the **Diagnostics logs** settings for the App Service, and change the **Docker Container logging** setting
from **Off** to **File System**. Logs are created in the container under */home/LogFiles/*_docker.log*, and can be accessed on the box using SSH or FTP(S).
* A custom domain name may be assigned to the site, rather than the *.azurewebsites.net URL assigned by default. For more details, see the topic [Map Custom Domain](https://docs.microsoft.com/en-us/azure/app-service/app-service-web-tutorial-custom-domain).
* Deploying to a staging site for further testing before moving into production is a best practice. For details on how to configure this, see the topic
[Create staging environments](https://docs.microsoft.com/en-us/azure/app-service/web-sites-staged-publishing).
* See the [App Service on Linux FAQ](https://docs.microsoft.com/en-us/azure/app-service/containers/app-service-linux-faq) for more commonly asked questions.
