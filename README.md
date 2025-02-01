### Blog Preview
**Blog Address:**

**Backend:**

This project is maintained long-term. Feel free to fork the code and give it a star!

## Introduction
I had the idea of creating a personal blog for a long time. After learning Spring Boot, I decided to put my knowledge into practice by developing this blog. This project is a front-end and back-end separated blog system based on **Spring Boot + Vue**.

## Frontend
### Core Framework:
- **Vue Framework**: Vue 2.6.11, Vue Router, Vuex, vue/cli 4.5.12
- **UI Framework**: Element UI
- **Asynchronous Requests**: axios

### Markdown Support:
- **Editor**: mavon-editor
- **Parsing & Rendering**: markdown-it-vue (slightly modified to support line numbers and a black background for code blocks)
- **Styling**: github-markdown-css (GitHub dark-style code highlighting)

## Backend
### Core Framework:
- **Spring Boot**

### Security:
- **Security Framework**: Shiro
- **Token Authentication**: JWT

### Database & Persistence:
- **ORM Framework**: MyBatis Plus
- **Java Version**: JDK 8

## Features
### Homepage
- **Latest Articles**: Displayed in descending order based on publishing time
- **Navigation Bar**: Home, Categories, Archives, Friends, About Me
- **Website Announcements**
- **Blogger Information Display**

### Admin Panel
- **Login Functionality**
- **Article Management**:
    - Publish Articles
    - Edit Articles
    - Delete Articles
- **Comment Functionality**
- **Visitor Statistics**
- **Mobile Adaptation**
- **UI Enhancements**
- **Permission Management**

## Future Enhancements
- **Theme Customization**
- **Backend Optimization**

## Quick Start
1. Execute `console.sql` to create the database
2. Start Redis
3. Modify the `yml` configuration in IntelliJ IDEA and run the backend project
4. Install `npm` and start the frontend in IntelliJ IDEA

## Deployment
- Use **Docker Compose** for orchestration
- Required components: **Nginx, Redis, Backend Project, MySQL**
- Configure an SSL certificate (you can apply for a one-year free certificate from Tencent or Alibaba)
- Use **Nginx reverse proxy** to forward HTTPS requests to the backend via HTTP, preventing direct API exposure and addressing HTTP security issues

## Acknowledgments
During development, I referred to many excellent blogs, such as **NBlog** and **MyBlog**. Special thanks to **MarkerHub’s videos**, which helped me get started with Spring Boot.  