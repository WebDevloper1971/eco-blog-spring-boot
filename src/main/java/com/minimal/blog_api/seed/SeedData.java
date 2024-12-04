package com.minimal.blog_api.seed;


import com.minimal.blog_api.dto.ReqRes;
import com.minimal.blog_api.dto.UserReqRes;
import com.minimal.blog_api.models.BlogPost;
import com.minimal.blog_api.models.BlogUser;
import com.minimal.blog_api.models.Role;
import com.minimal.blog_api.repository.BlogPostRepo;
import com.minimal.blog_api.repository.BlogUserRepo;
import com.minimal.blog_api.service.BlogPostService;
import com.minimal.blog_api.service.BlogUserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SeedData implements CommandLineRunner {

    @Autowired
    BlogPostService blogPostService;
    @Autowired
    BlogUserManagementService blogUserManagementService;

    @Autowired
    BlogUserRepo blogUserRepo;

    @Autowired
    BlogPostRepo blogPostRepo;

    @Override
    public void run(String... args) throws Exception {

        UserReqRes adminReq = new UserReqRes();
        adminReq.setUsername("admin");
        adminReq.setEmail("gpvedant2000@gmail.com");
        adminReq.setPassword("@VedantNPG2000@");
        adminReq.setSecret("Jai Shree Ram");
        blogUserManagementService.register(adminReq);
        BlogUser admin = blogUserRepo.findByEmail("gpvedant2000@gmail.com").get();


        
        BlogPost blogPost1 = new BlogPost();
        blogPost1.setTitle("Climate Change");
        blogPost1.setImg_url("https://images.unsplash.com/photo-1569163139394-de4e5f43e5ca?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");
        blogPost1.setDescription("The long-term alteration of temperature and typical weather patterns in a place, largely driven by human activities, such as burning fossil fuels, leading to global warming.");
        blogPost1.setContent("# Understanding Climate Change\n" +
                "\n" +
                "Climate change is one of the most pressing issues facing our planet today. It refers to significant changes in global temperatures and weather patterns over time. While climate change is a natural phenomenon, human activities have accelerated its pace in recent decades.\n" +
                "\n" +
                "## Causes of Climate Change\n" +
                "\n" +
                "There are several key factors contributing to climate change:\n" +
                "\n" +
                "- **Greenhouse Gas Emissions:** The burning of fossil fuels, deforestation, and industrial processes release greenhouse gases like carbon dioxide and methane into the atmosphere.\n" +
                "- **Deforestation:** Trees absorb carbon dioxide, so cutting them down reduces the Earth's capacity to manage CO2 levels.\n" +
                "- **Industrial Activities:** Factories and power plants release large amounts of CO2 and other pollutants.\n" +
                "- **Agricultural Practices:** Livestock farming produces methane, a potent greenhouse gas.\n" +
                "\n" +
                "## Effects of Climate Change\n" +
                "\n" +
                "The impacts of climate change are widespread and severe:\n" +
                "\n" +
                "- **Rising Temperatures:** Global temperatures are increasing, leading to more frequent and severe heatwaves.\n" +
                "- **Melting Ice Caps:** Polar ice caps and glaciers are melting, contributing to rising sea levels.\n" +
                "- **Extreme Weather:** Increased frequency and intensity of storms, hurricanes, and droughts.\n" +
                "- **Biodiversity Loss:** Many species are facing extinction due to changing habitats and temperatures.\n" +
                "\n" +
                "## Mitigation Strategies\n" +
                "\n" +
                "To combat climate change, various strategies can be employed:\n" +
                "\n" +
                "- **Renewable Energy:** Transitioning to solar, wind, and hydroelectric power to reduce reliance on fossil fuels.\n" +
                "- **Energy Efficiency:** Improving energy efficiency in homes, buildings, and transportation.\n" +
                "- **Reforestation:** Planting trees to absorb CO2 and restore natural ecosystems.\n" +
                "- **Sustainable Agriculture:** Adopting farming practices that reduce methane emissions and improve soil health.\n" +
                "\n" +
                "## Conclusion\n" +
                "\n" +
                "Climate change is a complex challenge that requires global cooperation and immediate action. By understanding its causes and effects, and implementing effective mitigation strategies, we can work towards a more sustainable future for our planet.\n");
        blogPost1.setCategory("climate change");
        blogPost1.setSlug("climate-change");
        blogPost1.setUserId(admin.getId());
        blogPost1.setUsername(admin.getUsername());
        blogPost1.setCreatedDate(new Date());
        blogPost1.setUpdatedDate(null);
        blogPostRepo.save(blogPost1);

        BlogPost blogPost2 = new BlogPost();
        blogPost2.setTitle("Understanding Solar Energy");
        blogPost2.setImg_url("https://images.unsplash.com/photo-1699794043417-afcdfd0a4493?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");
        blogPost2.setDescription("Energy harnessed from the sun's rays, converted into electricity or heat. It's a renewable, sustainable, and eco-friendly power source reducing reliance on fossil fuels.");
        blogPost2.setContent("# Understanding Solar Energy\n" +
                "\n" +
                "Solar energy is a renewable and sustainable source of power that has the potential to significantly reduce our dependence on fossil fuels. It harnesses energy from the sun and converts it into electricity or heat, providing a clean and efficient alternative to traditional energy sources.\n" +
                "\n" +
                "## Benefits of Solar Energy\n" +
                "\n" +
                "Solar energy offers numerous advantages:\n" +
                "\n" +
                "- **Renewable Source:** Solar energy is abundant and inexhaustible, making it a reliable source of power for the future.\n" +
                "- **Environmentally Friendly:** It produces no greenhouse gas emissions, reducing our carbon footprint and mitigating climate change.\n" +
                "- **Cost-Effective:** Over time, solar energy can save money on electricity bills and reduce energy costs.\n" +
                "- **Low Maintenance:** Solar panels require minimal maintenance and have a long lifespan, making them a practical investment.\n" +
                "- **Energy Independence:** By generating your own electricity, you can become less reliant on external energy suppliers and enjoy greater energy security.\n" +
                "\n" +
                "## Types of Solar Energy Systems\n" +
                "\n" +
                "There are several types of solar energy systems available:\n" +
                "\n" +
                "- **Photovoltaic (PV) Systems:** These systems convert sunlight directly into electricity using solar panels.\n" +
                "- **Solar Thermal Systems:** These systems capture and store heat from the sun, which can be used for heating water or buildings.\n" +
                "- **Concentrated Solar Power (CSP):** These systems use mirrors or lenses to focus sunlight onto a small area, generating heat that can be used to produce electricity.\n" +
                "\n" +
                "## Applications of Solar Energy\n" +
                "\n" +
                "Solar energy can be utilized in various ways:\n" +
                "\n" +
                "- **Residential Use:** Solar panels can be installed on rooftops to generate electricity for homes.\n" +
                "- **Commercial Use:** Businesses can harness solar power to reduce operational costs and promote sustainability.\n" +
                "- **Industrial Use:** Solar energy can be used in manufacturing processes and large-scale energy production.\n" +
                "- **Agricultural Use:** Solar power can be used to operate irrigation systems, greenhouses, and other agricultural equipment.\n" +
                "\n" +
                "## Conclusion\n" +
                "\n" +
                "Solar energy is a powerful and sustainable solution to the world's growing energy needs. By adopting solar energy systems, we can reduce our environmental impact, save money, and move towards a more sustainable future. Investing in solar energy is not just a smart choice for today, but a crucial step for ensuring a cleaner, greener planet for future generations.\n");
        blogPost2.setCategory("solar energy");
        blogPost2.setSlug("understanding-solar-energy");
        blogPost2.setUserId(admin.getId());
        blogPost2.setUsername(admin.getUsername());
        blogPost2.setCreatedDate(new Date());
        blogPost2.setUpdatedDate(null);
        blogPostRepo.save(blogPost2);


        BlogPost blogPost3 = new BlogPost();
        blogPost3.setTitle("Permaculture : A Holistic Approach");
        blogPost3.setImg_url("https://images.unsplash.com/photo-1581812978434-812581fa1ad1?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");
        blogPost3.setDescription("A sustainable agricultural approach that designs landscapes mimicking natural ecosystems, promoting biodiversity, reducing waste, and enhancing soil health through regenerative practices.");
        blogPost3.setContent("# Understanding Permaculture\n" +
                "\n" +
                "Permaculture is a holistic approach to designing sustainable and self-sufficient agricultural systems. It emphasizes working with natural ecosystems and using regenerative practices to create environmentally friendly, productive, and resilient landscapes.\n" +
                "\n" +
                "## Principles of Permaculture\n" +
                "\n" +
                "Permaculture is based on several core principles:\n" +
                "\n" +
                "- **Observe and Interact:** Understanding the natural environment and its patterns to design systems that work in harmony with nature.\n" +
                "- **Catch and Store Energy:** Utilizing renewable resources and storing surplus energy for future use.\n" +
                "- **Obtain a Yield:** Ensuring that the system produces a tangible benefit, whether it be food, energy, or other resources.\n" +
                "- **Apply Self-Regulation and Accept Feedback:** Continuously improving the system by monitoring its performance and making necessary adjustments.\n" +
                "- **Use and Value Renewable Resources:** Prioritizing the use of renewable resources and reducing dependence on non-renewable ones.\n" +
                "\n" +
                "## Benefits of Permaculture\n" +
                "\n" +
                "Permaculture offers numerous advantages:\n" +
                "\n" +
                "- **Sustainability:** It promotes the use of sustainable practices that enhance soil health, conserve water, and reduce waste.\n" +
                "- **Biodiversity:** Encourages the cultivation of diverse plant and animal species, fostering a more resilient and productive ecosystem.\n" +
                "- **Efficiency:** Optimizes the use of available resources, reducing the need for external inputs and minimizing environmental impact.\n" +
                "- **Resilience:** Builds resilient systems that can withstand environmental challenges and adapt to changing conditions.\n" +
                "- **Community Building:** Encourages collaboration and knowledge-sharing within communities, promoting social and environmental well-being.\n" +
                "\n" +
                "## Permaculture Practices\n" +
                "\n" +
                "Various techniques and practices are employed in permaculture:\n" +
                "\n" +
                "- **Companion Planting:** Growing different plants together to enhance growth and repel pests.\n" +
                "- **Mulching:** Using organic materials to cover soil, retain moisture, and improve fertility.\n" +
                "- **Rainwater Harvesting:** Capturing and storing rainwater for irrigation and other uses.\n" +
                "- **Composting:** Recycling organic waste to create nutrient-rich soil amendments.\n" +
                "- **Polyculture:** Growing multiple crops in the same space to mimic natural ecosystems and increase biodiversity.\n" +
                "\n" +
                "## Conclusion\n" +
                "\n" +
                "Permaculture is a powerful approach to sustainable agriculture that integrates ecological principles with practical farming techniques. By adopting permaculture practices, we can create productive, resilient, and environmentally friendly systems that support both human and ecological health. Embracing permaculture is a step towards a more sustainable and harmonious relationship with the natural world.\n");
        blogPost3.setCategory("permaculture");
        blogPost3.setSlug("permaculture-:-a-holistic-approach");
        blogPost3.setUserId(admin.getId());
        blogPost3.setUsername(admin.getUsername());
        blogPost3.setCreatedDate(new Date());
        blogPost3.setUpdatedDate(null);
        blogPostRepo.save(blogPost3);
    }
}
