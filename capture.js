const puppeteer = require('puppeteer');
const {ImgurClient} = require('imgur');
const fs = require('fs');
const buildNumber = process.argv[2];
const client = new ImgurClient({ clientId: "1f08a5051f5a4ea" });

async function captureScreenshotAndUpload() {
    const browser = await puppeteer.launch();
    const page = await browser.newPage();

    await page.setViewport({
        width: 1920,
        height: 1080,
    });

    await page.goto('https://tightly-poetic-beagle.ngrok-free.app/login');
     await new Promise(resolve => setTimeout(resolve, 15000));
    try {
        await page.click('#root > div > main > div > div > section.mb-4.border.border-gray-300.bg-white.drop-shadow-md > div > footer > button');
    } catch (error) {
        console.log(error)
    }

    await new Promise(resolve => setTimeout(resolve, 5000));

    try {
        await page.type('#j_username', 'convidado');
        await page.type('#j_password', '123456');
        await page.click('form[name="login"] > button[type="submit"]');
    } catch (error) {
        console.log(error)
    }

    await new Promise(resolve => setTimeout(resolve, 5000));
    await page.goto(`https://tightly-poetic-beagle.ngrok-free.app/job/Continuos-Test-UI/${buildNumber}/allure/`);
    await new Promise(resolve => setTimeout(resolve, 5000));

    await page.screenshot({ path: 'screenshot.png' });

    const response = await client.upload({
        image: fs.createReadStream('screenshot.png'),
        type: 'stream',
      });
      console.log(response.data.link);


    await browser.close();


    return response.data.link;
}


captureScreenshotAndUpload();